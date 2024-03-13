import {Locator, Page} from '@playwright/test';

export class DiagramViewPage {
    readonly backButton: Locator;
    readonly reviewNodeLink: Locator;
    readonly updateWorkflowDefinitionButton: Locator;

    constructor(page: Page) {
        this.backButton = page.getByRole('link', {name: 'Back'});
        this.reviewNodeLink = page.getByText('review', {exact: true});
        this.updateWorkflowDefinitionButton = page.getByRole('button', {
          name: 'Update',
        });
    }

    async clickReviewNodeLink() {
      await this.reviewNodeLink.click();
    }

    async goBack() {
		  await this.backButton.click();
	  }

    async updateWorkflowDefinition() {
      await this.updateWorkflowDefinitionButton.click();
    }
}