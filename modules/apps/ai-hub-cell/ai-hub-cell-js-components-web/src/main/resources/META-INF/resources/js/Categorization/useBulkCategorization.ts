/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {useCallback, useMemo, useRef, useState} from 'react';

import {DEFAULT_COUNT, runCategorizationAgent} from './runCategorizationAgent';
import {getCandidateCategories} from './services/getCandidateCategories';
import {getExistingTags} from './services/getExistingTags';
import {
	BulkCategorizationContext,
	BulkCategorizationItem,
	BulkCategorizationItemState,
	ECategorizationAgent,
	Suggestion,
} from './types';

export type BulkCategorizationMode =
	| 'applyAll'
	| 'done'
	| 'preview'
	| 'selecting';

export const REASON_NO_PERMISSION = 'no_permission';

export const REASON_NO_SUGGESTIONS = 'no_suggestions';

function createInitialStates(
	items: BulkCategorizationItem[]
): BulkCategorizationItemState[] {
	return items.map((item) => ({
		currentCategoryIds: [],
		currentTagNames: [],
		item,
		status: item.canUpdate === false ? 'skipped' : 'pending',
		suggestions: [],
		...(item.canUpdate === false ? {reason: REASON_NO_PERMISSION} : {}),
	}));
}

export default function useBulkCategorization({
	agent,
	applyItemCommit,
	count = DEFAULT_COUNT,
	items,
	resolveItemContext,
}: BulkCategorizationContext) {
	const [itemStates, setItemStates] = useState<BulkCategorizationItemState[]>(
		() => createInitialStates(items)
	);
	const [currentIndex, setCurrentIndex] = useState(0);
	const [mode, setMode] = useState<BulkCategorizationMode>('selecting');

	// itemStatesRef is the source of truth for the async orchestration loops;
	// patchItem updates it synchronously and mirrors into React state for render.

	const itemStatesRef = useRef(itemStates);

	const patchItem = useCallback(
		(index: number, patch: Partial<BulkCategorizationItemState>) => {
			const nextItemStates = [...itemStatesRef.current];

			nextItemStates[index] = {...nextItemStates[index], ...patch};

			itemStatesRef.current = nextItemStates;

			setItemStates(nextItemStates);
		},
		[]
	);

	const generate = useCallback(
		async (index: number) => {
			const {item} = itemStatesRef.current[index];

			patchItem(index, {reason: undefined, status: 'running'});

			try {
				const {content, currentCategoryIds, currentTagNames} =
					await resolveItemContext(item);

				const data =
					agent === ECategorizationAgent.AUTO_CATEGORIZE
						? {
								candidateCategories:
									await getCandidateCategories({
										classNameId: item.classNameId,
										cmsGroupId: item.cmsGroupId,
										scopeId: item.scopeId,
									}),
							}
						: {
								existingTags: await getExistingTags({
									cmsGroupId: item.cmsGroupId,
									scopeId: item.scopeId,
								}),
							};

				const suggestions = await runCategorizationAgent(agent, {
					content,
					count,
					...data,
				});

				patchItem(index, {
					currentCategoryIds: currentCategoryIds ?? [],
					currentTagNames: currentTagNames ?? [],
					status: suggestions.length ? 'ready' : 'skipped',
					suggestions,
					...(suggestions.length
						? {}
						: {reason: REASON_NO_SUGGESTIONS}),
				});

				return suggestions;
			}
			catch (error) {
				patchItem(index, {
					reason: (error as Error).message,
					status: 'failed',
				});

				return null;
			}
		},
		[agent, count, patchItem, resolveItemContext]
	);

	const commit = useCallback(
		async (index: number, suggestions: Suggestion[]) => {
			const {item} = itemStatesRef.current[index];

			try {
				await applyItemCommit(item, agent, suggestions);

				patchItem(index, {status: 'committed'});
			}
			catch (error) {
				patchItem(index, {
					reason: (error as Error).message,
					status: 'failed',
				});
			}
		},
		[agent, applyItemCommit, patchItem]
	);

	const applyAll = useCallback(
		async (indexes: number[]) => {
			for (const index of indexes) {
				const suggestions = await generate(index);

				if (suggestions?.length) {
					await commit(index, suggestions);
				}
			}

			setMode('done');
		},
		[commit, generate]
	);

	// Preview mode advances a cursor across the batch, generating suggestions
	// for the current asset and pausing on 'ready' (or 'failed') so the user can
	// accept/dismiss before committing. Terminal items (no_permission, etc.) are
	// skipped over automatically.

	const goToIndex = useCallback(
		async (index: number) => {
			let cursor = index;

			while (cursor < itemStatesRef.current.length) {
				const state = itemStatesRef.current[cursor];

				setCurrentIndex(cursor);

				if (state.status === 'pending') {
					const suggestions = await generate(cursor);

					if (suggestions === null || suggestions.length) {
						return;
					}
				}
				else if (
					state.status === 'ready' ||
					state.status === 'failed'
				) {
					return;
				}

				cursor += 1;
			}

			setMode('done');
		},
		[generate]
	);

	const startApplyAll = useCallback(() => {
		setMode('applyAll');

		applyAll(itemStatesRef.current.map((unused, index) => index));
	}, [applyAll]);

	const startPreview = useCallback(() => {
		setMode('preview');

		goToIndex(0);
	}, [goToIndex]);

	const commitCurrent = useCallback(
		async (suggestions: Suggestion[]) => {
			await commit(currentIndex, suggestions);

			goToIndex(currentIndex + 1);
		},
		[commit, currentIndex, goToIndex]
	);

	const skipCurrent = useCallback(() => {
		patchItem(currentIndex, {status: 'skipped'});

		goToIndex(currentIndex + 1);
	}, [currentIndex, goToIndex, patchItem]);

	const regenerateCurrent = useCallback(() => {
		generate(currentIndex);
	}, [currentIndex, generate]);

	const resumeFailed = useCallback(() => {
		const failedIndexes = itemStatesRef.current.reduce<number[]>(
			(indexes, state, index) => {
				if (state.status === 'failed') {
					indexes.push(index);
				}

				return indexes;
			},
			[]
		);

		if (!failedIndexes.length) {
			return;
		}

		failedIndexes.forEach((index) =>
			patchItem(index, {reason: undefined, status: 'pending'})
		);

		setMode('applyAll');

		applyAll(failedIndexes);
	}, [applyAll, patchItem]);

	const summary = useMemo(() => {
		let committed = 0;
		let failed = 0;
		let skipped = 0;

		itemStates.forEach((state) => {
			if (state.status === 'committed') {
				committed += 1;
			}
			else if (state.status === 'failed') {
				failed += 1;
			}
			else if (state.status === 'skipped') {
				skipped += 1;
			}
		});

		return {committed, failed, skipped};
	}, [itemStates]);

	return {
		commitCurrent,
		currentIndex,
		currentState: itemStates[currentIndex],
		itemStates,
		mode,
		regenerateCurrent,
		resumeFailed,
		skipCurrent,
		startApplyAll,
		startPreview,
		summary,
	};
}
