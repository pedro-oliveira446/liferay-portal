/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.model.listener.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.field.builder.TextObjectFieldBuilder;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.related.models.test.util.ObjectEntryTestUtil;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryVersionLocalService;
import com.liferay.object.test.util.ObjectDefinitionTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.WorkflowTaskManager;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pedro Leite
 */
@RunWith(Arquillian.class)
public class ObjectEntryVersionModelListenerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddObjectEntry() throws Exception {
		ObjectDefinition objectDefinition =
			ObjectDefinitionTestUtil.publishObjectDefinition(
				true, false, true,
				Collections.singletonList(
					new TextObjectFieldBuilder(
					).labelMap(
						LocalizedMapUtil.getLocalizedMap(
							RandomTestUtil.randomString())
					).name(
						"textObjectFieldName"
					).build()),
				ObjectDefinitionConstants.SCOPE_COMPANY);

		ObjectEntry objectEntry = ObjectEntryTestUtil.addObjectEntry(
			0, objectDefinition.getObjectDefinitionId(),
			HashMapBuilder.<String, Serializable>put(
				"textObjectFieldName", RandomTestUtil.randomString()
			).build());

		Map<String, Serializable> values1 = objectEntry.getValues();

		// Approved

		objectEntry = _updateObjectEntry(
			objectEntry, ServiceContextTestUtil.getServiceContext());

		Map<String, Serializable> values2 = objectEntry.getValues();

		_assertChildObjectEntryNotExists(objectEntry.getObjectEntryId());

		// Draft

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		objectEntry = _updateObjectEntry(objectEntry, serviceContext);

		_assertChildObjectEntryExists(
			values2, 2, objectEntry.getObjectEntryId());

		objectEntry = _updateObjectEntry(
			objectEntry, ServiceContextTestUtil.getServiceContext());

		_assertChildObjectEntryNotExists(objectEntry.getObjectEntryId());

		// Expired

		objectEntry = _objectEntryLocalService.expireObjectEntry(
			TestPropsValues.getUserId(), objectEntry.getObjectEntryId(),
			ServiceContextTestUtil.getServiceContext());

		_assertChildObjectEntryExists(
			values2, 2, objectEntry.getObjectEntryId());

		_objectEntryVersionLocalService.expireObjectEntryVersion(
			TestPropsValues.getUserId(), objectEntry, 2,
			ServiceContextTestUtil.getServiceContext());

		_assertChildObjectEntryExists(
			values1, 1, objectEntry.getObjectEntryId());

		objectEntry = _updateObjectEntry(
			objectEntry, ServiceContextTestUtil.getServiceContext());

		Map<String, Serializable> values3 = objectEntry.getValues();

		_assertChildObjectEntryNotExists(objectEntry.getObjectEntryId());

		// Pending

		_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(
			TestPropsValues.getUserId(), TestPropsValues.getCompanyId(), 0,
			objectDefinition.getClassName(), 0, 0, "Single Approver", 1);

		objectEntry = _updateObjectEntry(
			objectEntry, ServiceContextTestUtil.getServiceContext());

		_assertChildObjectEntryExists(
			values3, 4, objectEntry.getObjectEntryId());

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(TestPropsValues.getUser()));

			List<WorkflowTask> workflowTasks =
				_workflowTaskManager.getWorkflowTasksBySubmittingUser(
					TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
					false, 0, 1, null);

			WorkflowTask workflowTask = workflowTasks.get(0);

			_workflowTaskManager.assignWorkflowTaskToUser(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
				workflowTask.getWorkflowTaskId(), TestPropsValues.getUserId(),
				StringPool.BLANK, null, null);

			_workflowTaskManager.completeWorkflowTask(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
				workflowTask.getWorkflowTaskId(), Constants.APPROVE,
				StringPool.BLANK, null);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}

		_assertChildObjectEntryNotExists(objectEntry.getObjectEntryId());
	}

	private void _assertChildObjectEntryExists(
		Map<String, Serializable> expectedValues, int expectedVersion,
		long objectEntryId) {

		ObjectEntry objectEntry =
			_objectEntryLocalService.fetchObjectEntryByParentObjectEntryId(
				objectEntryId);

		Assert.assertNotNull(objectEntry);

		Assert.assertEquals(
			MapUtil.getString(expectedValues, "textObjectFieldName"),
			MapUtil.getString(objectEntry.getValues(), "textObjectFieldName"));

		Assert.assertEquals(expectedVersion, objectEntry.getVersion());
	}

	private void _assertChildObjectEntryNotExists(long objectEntryId) {
		Assert.assertNull(
			_objectEntryLocalService.fetchObjectEntryByParentObjectEntryId(
				objectEntryId));
	}

	private ObjectEntry _updateObjectEntry(
			ObjectEntry objectEntry, ServiceContext serviceContext)
		throws Exception {

		return _objectEntryLocalService.updateObjectEntry(
			objectEntry.getUserId(), objectEntry.getObjectEntryId(),
			HashMapBuilder.<String, Serializable>put(
				"textObjectFieldName", RandomTestUtil.randomString()
			).build(),
			serviceContext);
	}

	@Inject
	private ObjectEntryLocalService _objectEntryLocalService;

	@Inject
	private ObjectEntryVersionLocalService _objectEntryVersionLocalService;

	@Inject
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

	@Inject
	private WorkflowTaskManager _workflowTaskManager;

}