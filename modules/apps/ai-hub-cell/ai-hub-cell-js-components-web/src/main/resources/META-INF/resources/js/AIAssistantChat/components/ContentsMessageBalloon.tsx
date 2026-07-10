/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import ClayList from '@clayui/list';
import React from 'react';

import '../chat.scss';
import renderAIAssistantMessageMarkdown from '../utils/renderAIAssistantMessageMarkdown';

export interface Content {
	editURL: string;
	status: string;
	title: string;
}

interface ContentsMessageBalloonProps {
	contents: Content[];
	message: string;
}

const ContentsMessageBalloon: React.FC<ContentsMessageBalloonProps> = ({
	contents,
	message,
}) => {
	return (
		<div className="ai-assistant-chat__ai-assistant-message-balloon ai-assistant-chat__content-generation-balloon">
			<div className="ai-assistant-chat__content-generation-balloon-header">
				<ClayIcon spritemap={Liferay.Icons.spritemap} symbol="stars" />

				<div
					dangerouslySetInnerHTML={{
						__html: renderAIAssistantMessageMarkdown(message),
					}}
				/>
			</div>

			<ClayList className="ai-assistant-chat__content-generation-balloon-list">
				{contents.map((content) => (
					<ClayList.Item flex key={content.editURL}>
						<ClayList.ItemField>
							<span className="ai-assistant-chat__content-generation-balloon-icon">
								<ClayIcon
									spritemap={Liferay.Icons.spritemap}
									symbol="blogs"
								/>
							</span>
						</ClayList.ItemField>

						<ClayList.ItemField expand>
							<ClayList.ItemTitle>
								<a href={content.editURL}>{content.title}</a>
							</ClayList.ItemTitle>

							<ClayList.ItemText>
								<ClayLabel displayType="secondary">
									{content.status}
								</ClayLabel>
							</ClayList.ItemText>
						</ClayList.ItemField>
					</ClayList.Item>
				))}
			</ClayList>
		</div>
	);
};

export default ContentsMessageBalloon;
