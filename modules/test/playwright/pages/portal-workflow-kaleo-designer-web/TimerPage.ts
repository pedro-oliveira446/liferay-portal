/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

export class TimerPage {
	readonly deleteAllTimersButton: Locator;
	readonly inputTimerDescription: Locator;
	readonly inputTimerDuration: Locator;
	readonly inputTimerName: Locator;
	readonly inputTimerRecurrence: Locator;
	readonly inputTimerScale: Locator;
	readonly modalDeleteButton: Locator;
	readonly page: Page;

	constructor(page: Page) {
		this.deleteAllTimersButton = page.locator('.trash-button').nth(0);
		this.inputTimerDescription = page.locator('#timerDescription');
		this.inputTimerDuration = page
			.locator('div')
			.filter({hasText: /^Duration\*$/})
			.getByRole('spinbutton');
		this.inputTimerName = page.locator('#timerName');
		this.inputTimerRecurrence = page.getByLabel('Recurrence');
		this.inputTimerScale = page.locator('#scale');
		this.modalDeleteButton = page.getByRole('button', {name: 'Delete'});
		this.page = page;
	}

	async deleteAllTimers() {
		await this.deleteAllTimersButton.click();
		await this.modalDeleteButton.click();
	}
}
