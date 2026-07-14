/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import {sub} from 'frontend-js-web';
import React, {useEffect, useState} from 'react';

import CategorizationSuggestions from '../../Categorization/components/CategorizationSuggestions';
import {
	BulkCategorizationContext,
	BulkCategorizationItemState,
	CategorizationStatus,
	ECategorizationAgent,
	Suggestion,
} from '../../Categorization/types';
import useBulkCategorization, {
	REASON_NO_PERMISSION,
	REASON_NO_SUGGESTIONS,
} from '../../Categorization/useBulkCategorization';

function getKey(suggestion: Suggestion): string {
	return `${suggestion.id ?? suggestion.name}`;
}

function toSuggestionsStatus(
	itemStatus: BulkCategorizationItemState['status']
): CategorizationStatus {
	if (itemStatus === 'running' || itemStatus === 'pending') {
		return 'loading';
	}

	if (itemStatus === 'failed') {
		return 'error';
	}

	return 'ready';
}

function getReasonLabel(reason?: string): string {
	if (reason === REASON_NO_PERMISSION) {
		return Liferay.Language.get(
			'you-do-not-have-permission-to-edit-this-item'
		);
	}

	if (reason === REASON_NO_SUGGESTIONS) {
		return Liferay.Language.get('no-matching-suggestions-were-found');
	}

	return reason || Liferay.Language.get('an-unexpected-error-occurred');
}

function ItemStatusRow({state}: {state: BulkCategorizationItemState}) {
	let color = '#6B6C7E';
	let symbol = 'hr';

	if (state.status === 'committed') {
		color = '#287D3C';
		symbol = 'check-circle';
	}
	else if (state.status === 'failed') {
		color = '#DA1414';
		symbol = 'exclamation-circle';
	}
	else if (state.status === 'skipped') {
		color = '#B95000';
		symbol = 'warning-full';
	}

	return (
		<div className="align-items-center d-flex mb-1">
			<ClayIcon
				className="mr-2"
				color={color}
				spritemap={Liferay.Icons.spritemap}
				symbol={symbol}
			/>

			<span className="text-truncate">{state.item.title}</span>

			{(state.status === 'failed' || state.status === 'skipped') &&
			state.reason ? (
				<span className="ml-2 text-secondary">
					{`(${getReasonLabel(state.reason)})`}
				</span>
			) : null}
		</div>
	);
}

export default function BulkCategorizationMessageBalloon(
	props: BulkCategorizationContext
) {
	const {agent, items} = props;

	const {
		commitCurrent,
		currentIndex,
		currentState,
		itemStates,
		mode,
		regenerateCurrent,
		resumeFailed,
		skipCurrent,
		startApplyAll,
		startPreview,
		summary,
	} = useBulkCategorization(props);

	const [dismissed, setDismissed] = useState<string[]>([]);

	useEffect(() => {
		setDismissed([]);
	}, [currentIndex]);

	const isCategories = agent === ECategorizationAgent.AUTO_CATEGORIZE;

	const contextChip = (
		<div className="align-items-center d-flex font-weight-semi-bold mb-3">
			<ClayIcon
				className="mr-2"
				color="#0B5FFF"
				spritemap={Liferay.Icons.spritemap}
				symbol="stars"
			/>

			{sub(
				Liferay.Language.get('context-x-selected-items'),
				`${items.length}`
			)}
		</div>
	);

	let body = null;

	if (mode === 'selecting') {
		body = (
			<>
				<p>
					{isCategories
						? Liferay.Language.get(
								'i-can-suggest-categories-for-each-selected-asset-how-would-you-like-to-proceed'
							)
						: Liferay.Language.get(
								'i-can-suggest-tags-for-each-selected-asset-how-would-you-like-to-proceed'
							)}
				</p>

				<div className="d-flex justify-content-end">
					<ClayButton
						className="mr-2"
						displayType="secondary"
						onClick={startPreview}
					>
						{Liferay.Language.get('review-each-asset')}
					</ClayButton>

					<ClayButton displayType="primary" onClick={startApplyAll}>
						{Liferay.Language.get('apply-to-all')}
					</ClayButton>
				</div>
			</>
		);
	}
	else if (mode === 'preview' && currentState) {
		const visibleSuggestions = currentState.suggestions.filter(
			(suggestion) => !dismissed.includes(getKey(suggestion))
		);

		body = (
			<>
				<div className="font-weight-semi-bold mb-2">
					{sub(
						Liferay.Language.get('reviewing-x'),
						currentState.item.title
					)}
				</div>

				<CategorizationSuggestions
					kind={isCategories ? 'categories' : 'tags'}
					onCommit={(committedSuggestions) =>
						commitCurrent(committedSuggestions)
					}
					onDismiss={(suggestion) =>
						setDismissed((previousDismissed) => [
							...previousDismissed,
							getKey(suggestion),
						])
					}
					onRegenerate={() => {
						setDismissed([]);

						regenerateCurrent();
					}}
					status={toSuggestionsStatus(currentState.status)}
					suggestions={visibleSuggestions}
				/>

				<div className="d-flex justify-content-end mt-2">
					<ClayButton displayType="unstyled" onClick={skipCurrent}>
						{Liferay.Language.get('skip')}
					</ClayButton>
				</div>
			</>
		);
	}
	else if (mode === 'applyAll') {
		body = (
			<div>
				<p>
					{Liferay.Language.get('applying-suggestions-to-each-asset')}
				</p>

				{itemStates.map((state) => (
					<ItemStatusRow key={`${state.item.id}`} state={state} />
				))}
			</div>
		);
	}
	else if (mode === 'done') {
		body = (
			<div>
				<p className="font-weight-semi-bold">
					{Liferay.Language.get('all-done')}
				</p>

				<p>
					{sub(
						Liferay.Language.get('x-succeeded-x-skipped-x-failed'),
						`${summary.committed}`,
						`${summary.skipped}`,
						`${summary.failed}`
					)}
				</p>

				<div className="mb-2">
					{itemStates.map((state) => (
						<ItemStatusRow key={`${state.item.id}`} state={state} />
					))}
				</div>

				{summary.failed > 0 ? (
					<div className="d-flex justify-content-end">
						<ClayButton
							displayType="secondary"
							onClick={resumeFailed}
						>
							<ClayIcon
								className="mr-2"
								spritemap={Liferay.Icons.spritemap}
								symbol="reload"
							/>

							{Liferay.Language.get('resume-failed')}
						</ClayButton>
					</div>
				) : null}
			</div>
		);
	}

	return (
		<div className="ai-assistant-chat__ai-assistant-message-balloon mb-2 p-2 rounded">
			{contextChip}

			{body}
		</div>
	);
}
