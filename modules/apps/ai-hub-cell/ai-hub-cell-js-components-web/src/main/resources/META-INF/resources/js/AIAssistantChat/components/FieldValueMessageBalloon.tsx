/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import React from 'react';

import '../chat.scss';
import renderAIAssistantMessageMarkdown from '../utils/renderAIAssistantMessageMarkdown';

interface FieldValueMessageBalloonProps {
	message: string;
	onApply: () => void;
	onRegenerate: () => void;
}

function getGeneratedValue(message: string): string {
	try {
		const properties = JSON.parse(message) as Record<string, string>;

		return Object.values(properties).join('\n\n');
	}
	catch {
		return message;
	}
}

const FieldValueMessageBalloon: React.FC<FieldValueMessageBalloonProps> = ({
	message,
	onApply,
	onRegenerate,
}) => {
	return (
		<div className="ai-assistant-chat__ai-assistant-message-balloon d-flex flex-column mb-2 rounded">
			<div className="d-flex flex-row font-weight-semi-bold">
				<div className="align-items-start d-inline-block ml-2 mt-2 text-2 text-primary">
					<ClayIcon
						spritemap={Liferay.Icons.spritemap}
						symbol="stars"
					/>
				</div>

				<div
					className="m-2"
					dangerouslySetInnerHTML={{
						__html: renderAIAssistantMessageMarkdown(
							getGeneratedValue(message)
						),
					}}
				/>
			</div>

			<div className="d-flex mb-1 ml-2">
				<ClayButton displayType="primary" onClick={onApply} size="sm">
					{Liferay.Language.get('apply')}
				</ClayButton>

				<ClayButton
					className="ml-2"
					displayType="secondary"
					onClick={onRegenerate}
					size="sm"
				>
					{Liferay.Language.get('regenerate')}
				</ClayButton>
			</div>
		</div>
	);
};

export default FieldValueMessageBalloon;
