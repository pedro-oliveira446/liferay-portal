/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ai.hub.rest.internal.resource.v1_0;

import com.liferay.ai.hub.rest.dto.v1_0.Configuration;
import com.liferay.ai.hub.rest.manager.v1_0.ConfigurationManager;
import com.liferay.ai.hub.rest.resource.v1_0.ConfigurationResource;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Pedro Leite
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/configuration.properties",
	scope = ServiceScope.PROTOTYPE, service = ConfigurationResource.class
)
public class ConfigurationResourceImpl extends BaseConfigurationResourceImpl {

	@Override
	public Configuration patchConfigurationByExternalReferenceCode(
			String externalReferenceCode, Configuration configuration)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled(
				contextCompany.getCompanyId(), "LPD-62272")) {

			throw new UnsupportedOperationException();
		}

		return _configurationManager.patchConfiguration(
			contextCompany.getCompanyId(), configuration,
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(), null,
				_dtoConverterRegistry, contextHttpServletRequest, null,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser),
			externalReferenceCode);
	}

	@Reference
	private ConfigurationManager _configurationManager;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}