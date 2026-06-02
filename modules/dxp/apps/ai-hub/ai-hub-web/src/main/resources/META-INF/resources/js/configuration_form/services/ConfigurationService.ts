/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

import {Configuration} from '../types/Configuration';

const CONFIGURATION_BASE_URI = '/o/ai-hub/configurations';

const CONFIGURATION_BY_ERC_URI =
	'/o/ai-hub/v1.0/configurations/by-external-reference-code/';

const HEADERS = new Headers({
	'Accept': 'application/json',
	'Accept-Language': Liferay.ThemeDisplay.getBCP47LanguageId(),
	'Content-Type': 'application/json',
});

async function getConfiguration(externalReferenceCode: string) {
	const response = await fetch(
		`${CONFIGURATION_BY_ERC_URI}${externalReferenceCode}`,
		{
			headers: HEADERS,
			method: 'GET',
		}
	);

	if (!response.ok) {
		throw new Error('Failed to fetch configuration');
	}

	return response.json();
}

async function patchConfiguration(
	externalReferenceCode: string,
	configuration: Configuration
) {
	const response = await fetch(
		`${CONFIGURATION_BY_ERC_URI}${externalReferenceCode}`,
		{
			body: JSON.stringify(configuration),
			headers: HEADERS,
			method: 'PATCH',
		}
	);

	if (!response.ok) {
		const {message, title} = await response.json().catch(() => ({}));

		throw new Error(title || message || '');
	}

	return response.json();
}

export {getConfiguration, patchConfiguration};
