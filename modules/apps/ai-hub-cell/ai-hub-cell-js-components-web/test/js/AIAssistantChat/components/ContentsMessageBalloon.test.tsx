/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {render, screen} from '@testing-library/react';
import React from 'react';

import '@testing-library/jest-dom';

import ContentsMessageBalloon from '../../../../src/main/resources/META-INF/resources/js/AIAssistantChat/components/ContentsMessageBalloon';

describe('ContentsMessageBalloon', () => {
	it('renders each generated draft as a link with its status', () => {
		render(
			<ContentsMessageBalloon
				contents={[
					{
						editURL: '/edit/1',
						status: 'Draft',
						title: 'Travelling around Japan',
					},
					{
						editURL: '/edit/2',
						status: 'Draft',
						title: 'North Japan',
					},
				]}
				message="Done! Your drafts have been generated."
			/>
		);

		expect(
			screen.getByRole('link', {name: 'Travelling around Japan'})
		).toHaveAttribute('href', '/edit/1');
		expect(screen.getByRole('link', {name: 'North Japan'})).toHaveAttribute(
			'href',
			'/edit/2'
		);
		expect(screen.getAllByText('Draft')).toHaveLength(2);
	});
});
