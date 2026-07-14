/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {EventSource} from 'eventsource';

import {
	createCategorizationEventSource,
	postCategorizationAgentInstance,
} from './api';
import {CategorizationContext, ECategorizationAgent, Suggestion} from './types';
import {parseSuggestions} from './utils/parseSuggestions';

export const DEFAULT_COUNT = 3;

export function toRequestContext(
	agent: ECategorizationAgent,
	context: CategorizationContext
): Record<string, unknown> {
	const requestContext: Record<string, unknown> = {
		content: context.content,
		count: context.count ?? DEFAULT_COUNT,
	};

	if (agent === ECategorizationAgent.AUTO_CATEGORIZE) {
		requestContext.candidateCategories = JSON.stringify(
			context.candidateCategories ?? []
		);
	}
	else {
		requestContext.existingTags = JSON.stringify(
			context.existingTags ?? []
		);
	}

	return requestContext;
}

/**
 * Runs a categorization agent for a single asset over SSE and resolves with the
 * parsed suggestions. Unlike useCategorizationAgent (which is React-stateful and
 * single-asset), this is a plain promise so a caller can fan out over a batch.
 */
export function runCategorizationAgent(
	agent: ECategorizationAgent,
	context: CategorizationContext
): Promise<Suggestion[]> {
	return new Promise<Suggestion[]>((resolve, reject) => {
		let eventSource: EventSource | null = null;
		let settled = false;

		const settle = (callback: () => void) => {
			if (settled) {
				return;
			}

			settled = true;

			eventSource?.close();
			eventSource = null;

			callback();
		};

		const rejectWithError = (message?: string) =>
			settle(() =>
				reject(
					new Error(
						message ||
							Liferay.Language.get('an-unexpected-error-occurred')
					)
				)
			);

		createCategorizationEventSource()
			.then((source) => {
				if (settled) {
					source?.close();

					return;
				}

				if (!source) {
					rejectWithError();

					return;
				}

				eventSource = source;

				source.addEventListener('Subscribe', (event) => {
					postCategorizationAgentInstance({
						agent,
						context: toRequestContext(agent, context),
						sseEventSinkKey: event.data,
					}).catch(() => rejectWithError());
				});

				source.addEventListener(agent, (event) => {
					try {
						const dataJSON = JSON.parse(event.data);

						settle(() =>
							resolve(
								parseSuggestions(
									agent,
									dataJSON.data ?? '',
									context
								)
							)
						);
					}
					catch {
						rejectWithError();
					}
				});

				source.addEventListener('Agent Invocation Failed', (event) => {
					let text = '';

					try {
						text = JSON.parse(event.data).data;
					}
					catch {
						text = '';
					}

					rejectWithError(text);
				});
			})
			.catch(() => rejectWithError());
	});
}
