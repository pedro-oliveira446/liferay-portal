/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import CategorizationSuggestionService, {
	CategorizationCommitSuggestion,
} from '../../../common/services/CategorizationSuggestionService';
import {ISearchAssetObjectEntry} from '../../../common/types/AssetType';
import {OBJECT_ENTRY_FOLDER_CLASS_NAME} from '../../../common/utils/constants';
import {
	AUTO_CATEGORIZE_AGENT,
	GENERATE_TAGS_AGENT,
} from '../../info_panel/components/categorizationAgentEvents';
import ObjectEntryService from '../../info_panel/services/ObjectEntryService';

interface BulkCategorizationItem {
	canUpdate: boolean;
	classNameId?: number;
	cmsGroupId: number | string;
	getURL: string;
	id: number | string;
	scopeId: number;
	title: string;
	updateURL?: string;
}

function toBulkCategorizationItem(
	entry: ISearchAssetObjectEntry,
	cmsGroupId: number | string
): BulkCategorizationItem {
	return {
		canUpdate: Boolean(entry.actions?.update?.href),
		classNameId:
			entry.embedded?.systemProperties?.objectDefinitionBrief
				?.classNameId,
		cmsGroupId,
		getURL: entry.actions?.get?.href,
		id: entry.embedded?.id,
		scopeId: entry.embedded?.scopeId,
		title: entry.title,
		updateURL: entry.actions?.update?.href,
	};
}

async function resolveItemContext(item: BulkCategorizationItem) {
	const {data, error} = await ObjectEntryService.getObjectEntry(item.getURL);

	if (error || !data) {
		throw new Error(
			error || Liferay.Language.get('an-unexpected-error-occurred')
		);
	}

	return {
		content: data.contentRawText ?? '',
		currentCategoryIds: (data.taxonomyCategoryBriefs ?? []).map(
			(brief) => brief.taxonomyCategoryId
		),
		currentTagNames: data.keywords ?? [],
	};
}

async function applyItemCommit(
	item: BulkCategorizationItem,
	agent: string,
	suggestions: CategorizationCommitSuggestion[]
) {
	if (!item.updateURL) {
		throw new Error(
			Liferay.Language.get('you-do-not-have-permission-to-edit-this-item')
		);
	}

	const {data: objectEntry, error} = await ObjectEntryService.getObjectEntry(
		item.getURL
	);

	if (error || !objectEntry) {
		throw new Error(
			error || Liferay.Language.get('an-unexpected-error-occurred')
		);
	}

	if (agent === AUTO_CATEGORIZE_AGENT) {
		const currentCategoryIds = objectEntry.taxonomyCategoryIds?.length
			? objectEntry.taxonomyCategoryIds
			: (objectEntry.taxonomyCategoryBriefs ?? []).map(
					(brief) => brief.taxonomyCategoryId
				);

		const briefs =
			await CategorizationSuggestionService.resolveNewCategoryBriefs(
				suggestions,
				currentCategoryIds
			);

		if (!briefs.length) {
			return;
		}

		const {error: patchError} = await ObjectEntryService.patchObjectEntry(
			{
				taxonomyCategoryIds: [
					...new Set([
						...currentCategoryIds,
						...briefs.map((brief) => brief.taxonomyCategoryId),
					]),
				],
			},
			item.updateURL
		);

		if (patchError) {
			throw new Error(patchError);
		}
	}
	else {
		const names = await CategorizationSuggestionService.createTagNames(
			suggestions,
			{assetLibraryId: item.scopeId, cmsGroupId: item.cmsGroupId}
		);

		const {error: patchError} = await ObjectEntryService.patchObjectEntry(
			{
				keywords: [
					...new Set([...(objectEntry.keywords ?? []), ...names]),
				],
			},
			item.updateURL
		);

		if (patchError) {
			throw new Error(patchError);
		}
	}
}

export default function bulkCategorizationWithAIAction({
	agent,
	cmsGroupId,
	selectedData,
}: {
	agent: typeof AUTO_CATEGORIZE_AGENT | typeof GENERATE_TAGS_AGENT;
	cmsGroupId: number | string;
	selectedData: {items?: ISearchAssetObjectEntry[]};
}) {
	const items = (selectedData?.items ?? [])
		.filter(
			(entry) => entry.entryClassName !== OBJECT_ENTRY_FOLDER_CLASS_NAME
		)
		.map((entry) => toBulkCategorizationItem(entry, cmsGroupId));

	if (!items.length) {
		return;
	}

	Liferay.fire('openAIAssistantChat', {
		context: {
			bulkCategorization: {
				agent,
				applyItemCommit,
				items,
				resolveItemContext,
			},
		},
	});
}
