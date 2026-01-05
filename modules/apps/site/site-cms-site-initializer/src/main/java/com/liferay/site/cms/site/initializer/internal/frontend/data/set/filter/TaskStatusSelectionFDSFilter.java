/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.cms.site.initializer.internal.frontend.data.set.filter;

import com.liferay.frontend.data.set.filter.BaseSelectionFDSFilter;
import com.liferay.frontend.data.set.filter.FDSFilter;
import com.liferay.site.cms.site.initializer.internal.constants.CMSSiteInitializerFDSNames;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pedro Leite
 */
@Component(
	property = {
		"frontend.data.set.name=" + CMSSiteInitializerFDSNames.CMP_TASK,
		"service.ranking:Integer=" + Integer.MAX_VALUE
	},
	service = FDSFilter.class
)
public class TaskStatusSelectionFDSFilter extends BaseSelectionFDSFilter {

	@Override
	public String getAPIURL() {
		return "/o/headless-admin-list-type/v1.0/list-type-definitions" +
			"/by-external-reference-code/L_CMS_BULK_ACTION_EXECUTION_STATUSES" +
				"/list-type-entries ";
	}

	@Override
	public String getId() {
		return "id";
	}

	@Override
	public String getItemKey() {
		return "key";
	}

	@Override
	public String getItemLabel() {
		return "name";
	}

	@Override
	public String getLabel() {
		return "status";
	}

	@Override
	public boolean isAutocompleteEnabled() {
		return true;
	}

}