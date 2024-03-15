import {Locator, Page} from '@playwright/test';

export class SourceViewPage {
	readonly diagramViewButton: Locator;

	constructor(page: Page) {
		this.diagramViewButton = page.locator('button[title="Diagram View"]').first()
	}

	async clickDiagramViewButton() {
		await this.diagramViewButton.click();
	}
}