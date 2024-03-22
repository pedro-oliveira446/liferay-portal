/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

export class RoleTypePage {
	readonly InputAutoCreate: Locator;
	readonly inputRoleName: Locator;
	readonly inputRoleType: Locator;
	readonly newSectionButton: Locator;
	readonly page: Page;
	readonly sectionInputRoleName: Locator;
	readonly sectionInputRoleType: Locator;

	constructor(index: number, page: Page) {
		this.newSectionButton = page
			.getByRole('button', {name: 'New Section'})
			.nth(index);
		this.inputRoleName = page.getByLabel('Role Name');
		this.inputRoleType = page.getByLabel('Role Type');
		this.InputAutoCreate = page.getByRole('checkbox').nth(index);
		this.sectionInputRoleName = page.locator('#role-name').nth(index);
		this.sectionInputRoleType = page.locator('#role-type').nth(index);
		this.page = page;
	}

	async fillFirstFields(
		autocreate: boolean,
		roleName: string,
		roleType: string
	) {
		await this.inputRoleType.click();

		await this.page
			.getByRole('menuitem', {name: roleType, exact: true})
			.click();

		await this.inputRoleName.click();

		await this.page
			.getByRole('menuitem', {name: roleName, exact: true})
			.click();

		await this.markCheckbox(autocreate);
	}

	async fillSectionFields(
		autocreate: boolean,
		roleName: string,
		roleType: string
	) {
		await this.sectionInputRoleType.click();

		await this.page
			.getByRole('menuitem', {name: roleType, exact: true})
			.click();

		await this.sectionInputRoleName.click();

		await this.page
			.getByRole('menuitem', {name: roleName, exact: true})
			.click();

		await this.markCheckbox(autocreate);
	}

	async markCheckbox(autocreate: boolean){
		if(!autocreate){
			return;
		}
		this.InputAutoCreate.check()
	}

	async newSectionButtonCLick() {
		await this.newSectionButton.click();
	}
}
