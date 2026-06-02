/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ai.hub.rest.internal.manager.v1_0;

import com.liferay.ai.hub.rest.dto.v1_0.Configuration;
import com.liferay.ai.hub.rest.manager.v1_0.ConfigurationManager;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManager;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import java.util.Dictionary;

import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pedro Leite
 */
@Component(service = ConfigurationManager.class)
public class ConfigurationManagerImpl implements ConfigurationManager {

	@Override
	public Configuration patchConfiguration(
			long companyId, Configuration configuration,
			DTOConverterContext dtoConverterContext,
			String externalReferenceCode)
		throws Exception {

		ObjectEntry objectEntry = _objectEntryManager.partialUpdateObjectEntry(
			companyId, dtoConverterContext, externalReferenceCode,
			_objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_AI_HUB_CONFIGURATION", companyId),
			new ObjectEntry() {
				{
					setProperties(
						() -> HashMapBuilder.<String, Object>put(
							"environmentUrls",
							GetterUtil.getString(
								configuration.getEnvironmentUrls())
						).put(
							"recipientEmailAddress",
							GetterUtil.getString(
								configuration.getRecipientEmailAddress())
						).build());
				}
			},
			null);

		_updatePortalCORSConfiguration(
			GetterUtil.getString(configuration.getEnvironmentUrls()));

		return new Configuration() {
			{
				setEnvironmentUrls(
					() -> GetterUtil.getString(
						objectEntry.getPropertyValue("environmentUrls")));
				setExternalReferenceCode(objectEntry::getExternalReferenceCode);
				setRecipientEmailAddress(
					() -> GetterUtil.getString(
						objectEntry.getPropertyValue("recipientEmailAddress")));
			}
		};
	}

	private void _updatePortalCORSConfiguration(String environmentUrls)
		throws Exception {

		if (Validator.isNull(environmentUrls)) {
			return;
		}

		org.osgi.service.cm.Configuration[] configurations =
			_configurationAdmin.listConfigurations(
				String.format(
					"(&(service.factoryPid=%s)(configuration.name=%s))",
					_PORTAL_CORS_CONFIGURATION_PID,
					_AI_HUB_CELL_CONFIGURATION_NAME));

		if (ArrayUtil.isEmpty(configurations)) {
			return;
		}

		org.osgi.service.cm.Configuration configuration = configurations[0];

		Dictionary<String, Object> properties = configuration.getProperties();

		properties.put(
			"headers",
			TransformUtil.transform(
				GetterUtil.getStringValues(properties.get("headers")),
				header -> {
					if (!StringUtil.startsWith(
							header, "Access-Control-Allow-Origin:")) {

						return header;
					}

					return StringBundler.concat(
						header, StringPool.SPACE, environmentUrls);
				},
				String.class));

		configuration.update(properties);
	}

	private static final String _AI_HUB_CELL_CONFIGURATION_NAME = "AI Hub Cell";

	private static final String _PORTAL_CORS_CONFIGURATION_PID =
		"com.liferay.portal.remote.cors.configuration.PortalCORSConfiguration";

	@Reference
	private ConfigurationAdmin _configurationAdmin;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference(target = "(object.entry.manager.storage.type=default)")
	private ObjectEntryManager _objectEntryManager;

}
