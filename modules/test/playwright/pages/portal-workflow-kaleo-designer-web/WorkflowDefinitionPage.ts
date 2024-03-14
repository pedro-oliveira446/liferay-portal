/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {ApplicationsMenuPage} from '../product-navigation-applications-menu/ApplicationsMenuPage';

export class WorkflowDefinitionPage {
	readonly page: Page;
	readonly applicationsMenuPage: ApplicationsMenuPage;
	readonly singleAproverWorkflowDefinitionLink: Locator;

	constructor(page: Page) {
		this.applicationsMenuPage = new ApplicationsMenuPage(page);
		this.page = page;
		this.singleAproverWorkflowDefinitionLink = page.getByRole('link', {
			exact: true,
			name: 'Single Approver',
		});
	}

	async clickSingleAproverWorkflowDefinition() {
		await this.singleAproverWorkflowDefinitionLink.click();
	}

	async goto() {
		await this.applicationsMenuPage.goToProcessBuilder();
	}
}
