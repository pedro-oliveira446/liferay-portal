import {Locator, Page} from '@playwright/test';
import {ApplicationsMenuPage} from '../product-navigation-applications-menu/ApplicationsMenuPage';

export class WorkflowDefinitionPage {
    readonly applicationsMenuPage: ApplicationsMenuPage;
    readonly singleAproverWorkflowDefinitionLink: Locator;

    constructor(page: Page) {
        this.applicationsMenuPage = new ApplicationsMenuPage(page);
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