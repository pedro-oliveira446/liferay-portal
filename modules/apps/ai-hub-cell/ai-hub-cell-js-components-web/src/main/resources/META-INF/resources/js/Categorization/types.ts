/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

export enum ECategorizationAgent {
	AUTO_CATEGORIZE = 'L_AUTO_CATEGORIZE',
	GENERATE_TAGS = 'L_GENERATE_TAGS',
}

export const CATEGORIZATION_INTENT_AGENT = 'L_CATEGORIZATION_INTENT';

export type CategorizationStatus =
	| 'empty'
	| 'error'
	| 'idle'
	| 'loading'
	| 'ready';

export interface CandidateCategory {
	id: number;
	name: string;
	vocabulary: string;
}

export interface CategorizationContext {
	candidateCategories?: CandidateCategory[];
	content: string;
	count?: number;
	existingTags?: string[];
}

export interface IntentAction {
	agent: 'categorize' | 'tag';
	count: number;
	targets: string[];
}

export interface IntentVerdict {
	actions: IntentAction[];
	passthrough: boolean;
}

export interface Suggestion {
	id?: number;
	isNew?: boolean;
	name: string;
}

export type BulkCategorizationItemStatus =
	| 'committed'
	| 'failed'
	| 'pending'
	| 'ready'
	| 'running'
	| 'skipped';

export interface BulkCategorizationItem {
	canUpdate?: boolean;
	classNameId?: number;
	cmsGroupId: number | string;
	getURL: string;
	id: number | string;
	scopeId: number;
	title: string;
	updateURL?: string;
}

export interface BulkCategorizationItemContext {
	content: string;
	currentCategoryIds?: number[];
	currentTagNames?: string[];
}

export interface BulkCategorizationItemState {
	currentCategoryIds: number[];
	currentTagNames: string[];
	item: BulkCategorizationItem;
	reason?: string;
	status: BulkCategorizationItemStatus;
	suggestions: Suggestion[];
}

export interface BulkCategorizationContext {
	agent: ECategorizationAgent;
	applyItemCommit: (
		item: BulkCategorizationItem,
		agent: ECategorizationAgent,
		suggestions: Suggestion[]
	) => Promise<void>;
	count?: number;
	items: BulkCategorizationItem[];
	resolveItemContext: (
		item: BulkCategorizationItem
	) => Promise<BulkCategorizationItemContext>;
}
