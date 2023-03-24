/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayPanel from '@clayui/panel';
import {FrontendDataSet} from '@liferay/frontend-data-set-web';
import {
	AutoComplete,
	filterArrayByQuery,
	getLocalizableLabel,
	onActionDropdownItemClick,
	openToast,
} from '@liferay/object-js-components-web';
import {createResourceURL, fetch} from 'frontend-js-web';
import React, {ReactHTMLElement, useEffect, useMemo, useState} from 'react';

interface DefinitionOfTermsProps {
	baseResourceURL: string;
	objectDefinitions: ObjectDefinition[];
}

export interface Item {
	termLabel: string;
	termName: string;
}

interface ParentObjectRelationship {
	sectionLabel: string;
	termsResourcePath: number;
	terms?: Item[];
}

export function DefinitionOfTerms({
	baseResourceURL,
	objectDefinitions,
}: DefinitionOfTermsProps) {
	const [selectedEntity, setSelectedEntity] = useState<ObjectDefinition>();
	const [query, setQuery] = useState<string>('');

	const [parentObjectRelationship, setParentObjectRelationship] = useState<
		ParentObjectRelationship[]
	>();

	const [showFDS, setShowFDS] = useState(true);

	const [entityFields, setObjectFieldTerms] = useState<Item[]>([]);

	const filteredObjectDefinitions = useMemo(() => {
		if (objectDefinitions) {
			return filterArrayByQuery({
				array: objectDefinitions,
				query,
				str: 'label',
			});
		}
	}, [objectDefinitions, query]);

	const getObjectFieldTerms = async (objectDefinition: ObjectDefinition) => {
		const response = await fetch(
			createResourceURL(baseResourceURL, {
				objectDefinitionId: objectDefinition.id,
				p_p_resource_id:
					'/notification_templates/get_object_field_notification_template_terms',
			}).toString()
		);

		const responseJSON = (await response.json()) as Item[];

		setObjectFieldTerms(responseJSON);
	};

	const getObjectFieldRelatedTerms = async (relationshipId: number) => {
		const response = await fetch(
			createResourceURL(baseResourceURL, {
				objectDefinitionId: relationshipId,
				p_p_resource_id:
					'/notification_templates/get_object_field_notification_template_terms',
			}).toString()
		);

		const terms = (await response.json()) as Item[];

		const currentParentRelationship = parentObjectRelationship?.find(
			(relationship) => relationship.termsResourcePath === relationshipId
		) as ParentObjectRelationship;

		const currentParentRelationshipIndex = parentObjectRelationship?.indexOf(
			currentParentRelationship
		) as number;

		console.log(currentParentRelationshipIndex);

		const newParentRelationship = {
			...currentParentRelationship,
			terms,
		};

		const newParentObjectRelationship = parentObjectRelationship;

		newParentObjectRelationship?.splice(
			currentParentRelationshipIndex,
			1,
			newParentRelationship
		);

		setParentObjectRelationship(
			newParentObjectRelationship as ParentObjectRelationship[]
		);
	};

	const copyObjectFieldTerm = ({itemData}: {itemData: Item}) => {
		navigator.clipboard.writeText(itemData.termName);

		openToast({
			message: Liferay.Language.get('term-copied-successfully'),
			type: 'success',
		});
	};

	useEffect(() => {
		Liferay.on('copyObjectFieldTerm', copyObjectFieldTerm);

		return () => {
			Liferay.detach('copyObjectFieldTerm');
		};
	}, []);

	useEffect(() => {
		const makeFetch = async () => {
			const newParentObjectRelationships = await Promise.all(
				objectDefinitions.map(async (definition) => {
					return {
						sectionLabel: getLocalizableLabel(
							definition.defaultLanguageId,
							definition.label,
							definition.name
						),
						termsResourcePath: definition.id,
					};
				})
			);

			setParentObjectRelationship(newParentObjectRelationships);
		};

		makeFetch();
	}, [objectDefinitions]);

	console.log(parentObjectRelationship);

	return (
		<>
			<ClayPanel
				collapsable
				defaultExpanded
				displayTitle={Liferay.Language.get('definition-of-terms')}
				displayType="secondary"
				showCollapseIcon={true}
			>
				<ClayPanel.Body>
					<AutoComplete<ObjectDefinition>
						creationLanguageId={
							selectedEntity?.defaultLanguageId as Locale
						}
						emptyStateMessage={Liferay.Language.get(
							'no-entities-were-found'
						)}
						items={filteredObjectDefinitions ?? []}
						label={Liferay.Language.get('entity')}
						onChangeQuery={setQuery}
						onSelectItem={(item) => {
							getObjectFieldTerms(item);
							setSelectedEntity(item);
						}}
						query={query}
						value={getLocalizableLabel(
							selectedEntity?.defaultLanguageId as Locale,
							selectedEntity?.label,
							selectedEntity?.name as string
						)}
					>
						{({defaultLanguageId, label, name}) => (
							<div className="d-flex justify-content-between">
								<div>
									{getLocalizableLabel(
										defaultLanguageId,
										label,
										name
									)}
								</div>
							</div>
						)}
					</AutoComplete>

					<div id="lfr-notification-web__definition-of-terms-table">
						<FrontendDataSet
							id="DefinitionOfTermsTable"
							items={entityFields}
							itemsActions={[
								{
									href: 'copyObjectFieldTerm',
									id: 'copyObjectFieldTerm',
									label: Liferay.Language.get('copy'),
									target: 'event',
								},
							]}
							onActionDropdownItemClick={
								onActionDropdownItemClick
							}
							selectedItemsKey="id"
							showManagementBar={false}
							showPagination={false}
							showSearch={false}
							views={[
								{
									contentRenderer: 'table',
									label: 'Table',
									name: 'table',
									schema: {
										fields: [
											{
												fieldName: 'termLabel',
												label: Liferay.Language.get(
													'label'
												),
											},
											{
												fieldName: 'termName',
												label: Liferay.Language.get(
													'term'
												),
											},
										],
									},
									thumbnail: 'table',
								},
							]}
						/>
					</div>
				</ClayPanel.Body>
			</ClayPanel>

			{parentObjectRelationship?.map((parentObjectRelationship) => (
				<ClayPanel
					collapsable
					defaultExpanded={false}
					displayTitle={parentObjectRelationship.sectionLabel}
					displayType="unstyled"
					onClick={(event) => {
						const element = event.target as HTMLButtonElement;

						const attribute = element.getAttribute('aria-expanded');

						if (attribute === 'false') {
							getObjectFieldRelatedTerms(
								parentObjectRelationship.termsResourcePath
							);

							setShowFDS(false);

							setTimeout(() => setShowFDS(true), 500);
						}
						console.log(attribute);
					}}
					showCollapseIcon={true}
				>
					{showFDS ? (
						<FrontendDataSet
							id="DefinitionOfTermsTable"
							items={parentObjectRelationship.terms ?? []}
							itemsActions={[
								{
									href: 'copyObjectFieldTerm',
									id: 'copyObjectFieldTerm',
									label: Liferay.Language.get('copy'),
									target: 'event',
								},
							]}
							onActionDropdownItemClick={
								onActionDropdownItemClick
							}
							selectedItemsKey="id"
							showManagementBar={false}
							showPagination={false}
							showSearch={false}
							views={[
								{
									contentRenderer: 'table',
									label: 'Table',
									name: 'table',
									schema: {
										fields: [
											{
												fieldName: 'termLabel',
												label: Liferay.Language.get(
													'label'
												),
											},
											{
												fieldName: 'termName',
												label: Liferay.Language.get(
													'term'
												),
											},
										],
									},
									thumbnail: 'table',
								},
							]}
						/>
					) : (
						<ClayLoadingIndicator
							displayType="secondary"
							size="sm"
						/>
					)}
				</ClayPanel>
			))}
		</>
	);
}
