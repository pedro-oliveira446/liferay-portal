/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {mergeTests} from '@playwright/test';

import {loginTest} from '../../fixtures/loginTest';
import {workflowPagesTest} from '../../fixtures/workflowPagesTest';
import {getRandomInt} from '../../utils/getRandomInt';

export const test = mergeTests(loginTest(), workflowPagesTest);

const timerNotifications = [
	{
		notificationDescription: 'notificationDescription0' + getRandomInt(),
		notificationName: 'notificationName0' + getRandomInt(),
		notificationTypeEmail: true,
		notificationTypeUser: true,
		recipientType: 'role',
		recipientTypeData: {
			roleName: 'Account Manager',
		},
		template: 'template0' + getRandomInt(),
		templateLanguage: 'freemarker',
	},
	{
		notificationDescription: 'notificationDescription1' + getRandomInt(),
		notificationName: 'notificationName1' + getRandomInt(),
		notificationTypeEmail: true,
		notificationTypeUser: true,
		recipientType: 'scriptedRecipient',
		recipientTypeData: {
			script: 'script' + getRandomInt(),
			scriptLanguage: 'java',
		},
		template: 'template1' + getRandomInt(),
		templateLanguage: 'text',
	},
] as Notification[];

test('LPD-16281 can create timer notifications', async ({
	diagramViewPage,
	nodePropertiesSidebarPage,
	workflowDefinitionPage,
}) => {
	await workflowDefinitionPage.goto();

	await workflowDefinitionPage.clickSingleAproverWorkflowDefinition();

	await diagramViewPage.clickReviewNodeLink();
	
	await nodePropertiesSidebarPage.createTimerNotification(timerNotifications);
});
