/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {mergeTests} from '@playwright/test';
import {loginTest} from '../../fixtures/loginTest';
import {workflowPagesTest} from '../../fixtures/workflowPagesTest';

export const test = mergeTests(
	loginTest(),
	workflowPagesTest
);

test('LPD-16281 can create timer notifications', async ({page,workflowDefinitionPage}) => {
    await workflowDefinitionPage.goto();
	
    await workflowDefinitionPage.clickSingleAproverWorkflowDefinition();
});
