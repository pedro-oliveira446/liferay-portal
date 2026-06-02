/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ai.hub.rest.resource.v1_0.test;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountEntryUserRelLocalService;
import com.liferay.ai.hub.rest.client.dto.v1_0.Configuration;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.rest.dto.v1_0.ObjectEntry;
import com.liferay.object.rest.manager.v1_0.ObjectEntryManager;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.site.initializer.SiteInitializer;
import com.liferay.site.initializer.SiteInitializerRegistry;

import java.util.Dictionary;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Pedro Leite
 */
@FeatureFlag("LPD-62272")
@RunWith(Arquillian.class)
public class ConfigurationResourceTest
	extends BaseConfigurationResourceTestCase {

	@BeforeClass
	public static void setUpClass() throws Exception {
		_accountEntry = _accountEntryLocalService.addAccountEntry(
			RandomTestUtil.randomString(), TestPropsValues.getUserId(),
			AccountConstants.PARENT_ACCOUNT_ENTRY_ID_DEFAULT,
			RandomTestUtil.randomString(), null, null,
			RandomTestUtil.randomString() + "@liferay.com", null, null,
			AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS,
			WorkflowConstants.STATUS_APPROVED,
			ServiceContextTestUtil.getServiceContext());

		_accountEntryUserRelLocalService.addAccountEntryUserRel(
			_accountEntry.getAccountEntryId(), TestPropsValues.getUserId());

		_dtoConverterContext = new DefaultDTOConverterContext(
			false, Map.of(), _dtoConverterRegistry, null,
			LocaleUtil.getDefault(), null, TestPropsValues.getUser());

		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(TestPropsValues.getUser()));

		_originalName = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(TestPropsValues.getUserId());

		ServiceContextThreadLocal.pushServiceContext(
			ServiceContextTestUtil.getServiceContext(
				TestPropsValues.getGroupId(), TestPropsValues.getUserId()));

		SiteInitializer siteInitializer =
			_siteInitializerRegistry.getSiteInitializer(
				"com.liferay.ai.hub.site.initializer");

		siteInitializer.initialize(TestPropsValues.getGroupId());

		AccountEntry aiHubAccountEntry =
			_accountEntryLocalService.getAccountEntryByExternalReferenceCode(
				"L_AI_HUB", TestPropsValues.getCompanyId());

		_accountEntryUserRelLocalService.addAccountEntryUserRel(
			aiHubAccountEntry.getAccountEntryId(), TestPropsValues.getUserId());
	}

	@AfterClass
	public static void tearDownClass() {
		PermissionThreadLocal.setPermissionChecker(_originalPermissionChecker);

		PrincipalThreadLocal.setName(_originalName);

		ServiceContextThreadLocal.popServiceContext();
	}

	@Override
	@Test
	public void testPutConfigurationByExternalReferenceCode() throws Exception {
		String header = "Access-Control-Allow-Origin: http://localhost:8080";

		org.osgi.service.cm.Configuration configuration =
			_configurationAdmin.createFactoryConfiguration(
				_PORTAL_CORS_CONFIGURATION_PID + ".scoped", "?");

		configuration.update(
			HashMapDictionaryBuilder.<String, Object>put(
				"companyId", TestPropsValues.getCompanyId()
			).put(
				"filter.mapping.url.pattern", new String[] {"/o/ai-hub/*"}
			).put(
				"headers", new String[] {header}
			).build());

		String externalReferenceCode =
			_accountEntry.getAccountEntryId() + "-ai-hub-configuration";

		_objectEntryManager.addObjectEntry(
			_dtoConverterContext,
			_objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_AI_HUB_CONFIGURATION", TestPropsValues.getCompanyId()),
			new ObjectEntry() {
				{
					setExternalReferenceCode(externalReferenceCode);
					setProperties(
						() -> HashMapBuilder.<String, Object>put(
							"r_accountToAIHubConfigurations_accountEntryId",
							_accountEntry.getAccountEntryId()
						).build());
				}
			},
			null);

		Configuration randomConfiguration = randomConfiguration();

		String expectedHeader = StringBundler.concat(
			header, StringPool.SPACE, randomConfiguration.getEnvironmentUrls());

		configurationResource.putConfigurationByExternalReferenceCode(
			externalReferenceCode, randomConfiguration);

		_assertConfiguration(expectedHeader);

		configurationResource.putConfigurationByExternalReferenceCode(
			externalReferenceCode, randomConfiguration);

		_assertConfiguration(expectedHeader);

		configuration.delete();
	}

	private void _assertConfiguration(String expectedHeader) throws Exception {
		org.osgi.service.cm.Configuration[] configurations =
			_configurationAdmin.listConfigurations(
				StringBundler.concat(
					"(&(service.factoryPid=", _PORTAL_CORS_CONFIGURATION_PID,
					".scoped)(companyId=", TestPropsValues.getCompanyId(),
					"))"));

		Dictionary<String, Object> properties =
			configurations[0].getProperties();

		Assert.assertTrue(
			ArrayUtil.exists(
				GetterUtil.getStringValues(properties.get("headers")),
				header -> StringUtil.equals(expectedHeader, header)));
	}

	private static final String _PORTAL_CORS_CONFIGURATION_PID =
		"com.liferay.portal.remote.cors.configuration.PortalCORSConfiguration";

	private static AccountEntry _accountEntry;

	@Inject
	private static AccountEntryLocalService _accountEntryLocalService;

	@Inject
	private static AccountEntryUserRelLocalService
		_accountEntryUserRelLocalService;

	private static DTOConverterContext _dtoConverterContext;

	@Inject
	private static DTOConverterRegistry _dtoConverterRegistry;

	private static String _originalName;
	private static PermissionChecker _originalPermissionChecker;

	@Inject
	private static SiteInitializerRegistry _siteInitializerRegistry;

	@Inject
	private ConfigurationAdmin _configurationAdmin;

	@Inject
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Inject(filter = "object.entry.manager.storage.type=default")
	private ObjectEntryManager _objectEntryManager;

}