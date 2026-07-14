/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayForm, {ClaySelectWithOption} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import {fetch} from 'frontend-js-web';
import React, {useId, useState} from 'react';

import '../chat.scss';

export interface ContentType {
	externalReferenceCode: string;
	label: string;
	name: string;
}

interface ContentTypeSelectorMessageBalloonProps {
	contentTypes: ContentType[];
	message: string;
	onSelect: (
		objectDefinitionName: string,
		objectFields: string,
		label: string
	) => void;
}

const ContentTypeSelectorMessageBalloon: React.FC<
	ContentTypeSelectorMessageBalloonProps
> = ({contentTypes, message, onSelect}) => {
	const [externalReferenceCode, setExternalReferenceCode] = useState('');
	const [submitted, setSubmitted] = useState(false);

	const selectId = useId();

	function handleChange(event: React.ChangeEvent<HTMLSelectElement>) {
		const value = event.target.value;

		setExternalReferenceCode(value);

		const contentType = contentTypes.find(
			(contentType) => contentType.externalReferenceCode === value
		);

		if (!contentType) {
			return;
		}

		fetch(
			`/o/object-admin/v1.0/object-definitions/by-external-reference-code/${contentType.externalReferenceCode}/object-fields?fields=businessType,name,readOnly,required&pageSize=100`
		)
			.then((response) => response.json())
			.then((response) => {
				setSubmitted(true);

				onSelect(
					contentType.name,
					JSON.stringify(response),
					contentType.label
				);
			});
	}

	return (
		<div className="ai-assistant-chat__ai-assistant-message-balloon ai-assistant-chat__content-generation-balloon">
			<div className="ai-assistant-chat__content-generation-balloon-header">
				<ClayIcon spritemap={Liferay.Icons.spritemap} symbol="stars" />

				<span>{message}</span>
			</div>

			<div className="ai-assistant-chat__content-generation-balloon-form">
				<ClayForm.Group>
					<label htmlFor={selectId}>
						{Liferay.Language.get('content-type')}
					</label>

					<ClaySelectWithOption
						disabled={submitted}
						id={selectId}
						onChange={handleChange}
						options={[
							{
								disabled: true,
								label: Liferay.Language.get(
									'choose-a-content-type'
								),
								value: '',
							},
							...contentTypes.map((contentType) => ({
								label: contentType.label,
								value: contentType.externalReferenceCode,
							})),
						]}
						value={externalReferenceCode}
					/>
				</ClayForm.Group>
			</div>
		</div>
	);
};

export default ContentTypeSelectorMessageBalloon;
