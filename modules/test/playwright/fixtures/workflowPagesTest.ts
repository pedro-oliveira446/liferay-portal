/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {test} from '@playwright/test';

import {DiagramViewPage} from '../pages/portal-workflow-kaleo-designer-web/DiagramViewPage';
import {NodePropertiesSidebarPage} from '../pages/portal-workflow-kaleo-designer-web/NodePropertiesSidebarPage';
import {TimerPage} from '../pages/portal-workflow-kaleo-designer-web/TimerPage';
import {WorkflowTasksPage} from '../tests/portal-workflow-task-web/pages/WorkflowTasksPage';
import {WorkflowPage} from '../tests/portal-workflow-web/pages/WorkflowPage';
import {WorkflowDefinitionPage} from '../pages/portal-workflow-kaleo-designer-web/WorkflowDefinitionPage';

const workflowPagesTest = test.extend<{
	diagramViewPage: DiagramViewPage;
	nodePropertiesSidebarPage: NodePropertiesSidebarPage;
	timerPage: TimerPage;
	workflowPage: WorkflowPage;
	workflowDefinitionPage: WorkflowDefinitionPage;
	workflowTasksPage: WorkflowTasksPage;
}>({
	diagramViewPage: async ({page}, use) => {
		await use(new DiagramViewPage(page));
	},
	nodePropertiesSidebarPage: async ({page}, use) => {
		await use(new NodePropertiesSidebarPage(page));
	},
	timerPage: async ({page}, use) => {
		await use(new TimerPage(page));
	},
	workflowPage: async ({page}, use) => {
		await use(new WorkflowPage(page));
	},
	workflowDefinitionPage: async ({page}, use) => {
		await use(new WorkflowDefinitionPage(page));
	},
	workflowTasksPage: async ({page}, use) => {
		await use(new WorkflowTasksPage(page));
	},
});

export {workflowPagesTest};
