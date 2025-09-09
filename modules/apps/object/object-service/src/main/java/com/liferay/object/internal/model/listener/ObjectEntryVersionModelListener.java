/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.model.listener;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectEntryVersion;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryVersionLocalService;
import com.liferay.object.util.comparator.ObjectEntryVersionVersionComparator;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.security.audit.event.generators.constants.EventTypes;
import com.liferay.portal.security.audit.event.generators.util.AuditMessageBuilder;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuri Monteiro
 */
@Component(service = ModelListener.class)
public class ObjectEntryVersionModelListener
	extends BaseModelListener<ObjectEntryVersion> {

	@Override
	public void onAfterRemove(ObjectEntryVersion objectEntryVersion)
		throws ModelListenerException {

		try {
			ObjectDefinition objectDefinition =
				_objectDefinitionLocalService.getObjectDefinition(
					objectEntryVersion.getObjectDefinitionId());

			if (!objectDefinition.isEnableObjectEntryHistory()) {
				return;
			}

			AuditMessage auditMessage = AuditMessageBuilder.buildAuditMessage(
				EventTypes.DELETE, objectEntryVersion, null);

			JSONObject additionalInfoJSONObject =
				auditMessage.getAdditionalInfo();

			JSONObject contentJSONObject = _jsonFactory.createJSONObject(
				objectEntryVersion.getContent());

			JSONObject propertiesJSONObject = contentJSONObject.getJSONObject(
				"properties");

			for (String key : propertiesJSONObject.keySet()) {
				additionalInfoJSONObject.put(
					key, propertiesJSONObject.get(key));
			}

			_auditRouter.route(auditMessage);
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onAfterUpdate(
			ObjectEntryVersion originalObjectEntryVersion,
			ObjectEntryVersion objectEntryVersion)
		throws ModelListenerException {

		try {
			ObjectDefinition objectDefinition =
				_objectDefinitionLocalService.getObjectDefinition(
					objectEntryVersion.getObjectDefinitionId());

			if (!objectDefinition.isEnableObjectEntryVersioning()) {
				return;
			}

			ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(
				objectEntryVersion.getObjectEntryId());

			if (objectEntry.isApproved()) {
				ObjectEntry existingObjectEntry =
					_objectEntryLocalService.
						fetchObjectEntryByParentObjectEntryId(
							objectEntry.getObjectEntryId());

				if (existingObjectEntry != null) {
					_objectEntryLocalService.deleteObjectEntry(
						existingObjectEntry);
				}

				return;
			}

			ObjectEntryVersion latestObjectEntryVersion =
				_objectEntryVersionLocalService.
					fetchLatestApprovedObjectEntryVersion(
						objectEntry.getObjectEntryId(),
						ObjectEntryVersionVersionComparator.getInstance(false));

			if (latestObjectEntryVersion == null) {
				return;
			}

			ObjectEntry existingObjectEntry =
				_objectEntryLocalService.fetchObjectEntryByParentObjectEntryId(
					objectEntry.getObjectEntryId());

			if (existingObjectEntry != null) {
				if (existingObjectEntry.getVersion() ==
						latestObjectEntryVersion.getVersion()) {

					return;
				}

				_objectEntryLocalService.deleteObjectEntry(existingObjectEntry);
			}

			com.liferay.object.rest.dto.v1_0.ObjectEntry contentObjectEntry =
				com.liferay.object.rest.dto.v1_0.ObjectEntry.unsafeToDTO(
					latestObjectEntryVersion.getContent());

			Map<String, Object> properties = contentObjectEntry.getProperties();

			_objectEntryLocalService.addObjectEntry(
				null, objectEntry.getGroupId(), objectEntry.getUserId(),
				objectDefinition, objectEntry.getObjectEntryFolderId(),
				objectEntry.getObjectEntryId(),
				latestObjectEntryVersion.getVersion(),
				(Map<String, Serializable>)properties.get("properties"));
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private AuditRouter _auditRouter;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryVersionLocalService _objectEntryVersionLocalService;

}