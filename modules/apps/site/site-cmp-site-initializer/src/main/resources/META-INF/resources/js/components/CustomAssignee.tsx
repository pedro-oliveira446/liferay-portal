/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	Assignee,
	AssigneeValue,
} from '@liferay/object-dynamic-data-mapping-form-field-type';
import React, {useCallback, useEffect, useState} from 'react';

import AssigneeTrigger from './AssigneeTrigger';

import './AssigneeTrigger.scss';
import {putAssetLibraryUserAccount} from '../utils/api';
import {displayErrorToast} from '../utils/toastUtil';

interface ICustomAssignee {
	groupExternalReferenceCode: string;
	label?: string;
	name?: string;
	onChange?: (value: AssigneeValue | {}) => void;
	searchURL?: string;
	showLabel?: boolean;
	triggerClassName?: string;
	usersOnly?: boolean;
	value: AssigneeValue | {} | null;
}

export default function CustomAssignee({
	groupExternalReferenceCode,
	label,
	name,
	onChange,
	searchURL,
	showLabel = true,
	triggerClassName,
	usersOnly = false,
	value: initialValue,
}: ICustomAssignee) {
	const [value, setValue] = useState<AssigneeValue | null | {}>(initialValue);

	function getValue(
		usersOnly: boolean,
		value: AssigneeValue | null | {}
	): string {
		if (!usersOnly) {
			return JSON.stringify(value ?? {});
		}

		if (!value || !('id' in value)) {
			return '0';
		}

		return String(value.id);
	}

	const updateMemberships = useCallback(async () => {
		if (!usersOnly || !value || !('externalReferenceCode' in value)) {
			return;
		}

		const {error} = await putAssetLibraryUserAccount(
			groupExternalReferenceCode,
			value.externalReferenceCode
		);

		if (!error) {
			return;
		}

		setValue('');

		displayErrorToast(error as string);
	}, [value]);

	useEffect(() => {
		updateMemberships();
	}, [updateMemberships]);

	return (
		<>
			<Assignee
				label={label ?? Liferay.Language.get('assignee')}
				name=""
				onChange={async (event: {
					target: {value: AssigneeValue | {}};
				}) => {
					setValue(event.target.value);
					onChange?.(event.target.value);
				}}
				searchURL={
					searchURL ??
					Liferay.ThemeDisplay.getPortalURL() +
						'/o/headless-cmp/v1.0/task-assignees/'
				}
				showLabel={showLabel}
				triggerClassName={triggerClassName}
				triggerComponent={AssigneeTrigger}
				value={value}
				visible={true}
			/>
			<input
				name={name}
				type="hidden"
				value={getValue(usersOnly, value)}
			/>
		</>
	);
}
