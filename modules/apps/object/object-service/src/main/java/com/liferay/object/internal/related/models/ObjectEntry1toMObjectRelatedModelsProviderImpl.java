/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.related.models;

import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.entry.util.ObjectEntryThreadLocal;
import com.liferay.object.exception.RequiredObjectRelationshipException;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.related.models.ObjectRelatedModelsProvider;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.CurrentConnectionUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Marco Leo
 * @author Brian Wing Shun Chan
 */
public class ObjectEntry1toMObjectRelatedModelsProviderImpl
	implements ObjectRelatedModelsProvider<ObjectEntry> {

	public ObjectEntry1toMObjectRelatedModelsProviderImpl(
		ObjectDefinition objectDefinition,
		ObjectDefinitionLocalService objectDefinitionLocalService,
		ObjectEntryService objectEntryService,
		ObjectEntryLocalService objectEntryLocalService,
		ObjectFieldLocalService objectFieldLocalService,
		ObjectRelationshipLocalService objectRelationshipLocalService,
		ResourcePermissionLocalService resourcePermissionLocalService) {

		_objectDefinitionLocalService = objectDefinitionLocalService;
		_objectEntryService = objectEntryService;
		_objectEntryLocalService = objectEntryLocalService;
		_objectFieldLocalService = objectFieldLocalService;
		_objectRelationshipLocalService = objectRelationshipLocalService;
		_resourcePermissionLocalService = resourcePermissionLocalService;

		_className = objectDefinition.getClassName();
		_companyId = objectDefinition.getCompanyId();
	}

	@Override
	public void deleteRelatedModel(
			long userId, long groupId, long objectRelationshipId,
			long primaryKey, String deletionType)
		throws PortalException {

		ObjectRelationship objectRelationship =
			_objectRelationshipLocalService.getObjectRelationship(
				objectRelationshipId);

		ObjectField objectField = _objectFieldLocalService.getObjectField(
			objectRelationship.getObjectFieldId2());

		ObjectDefinition objectDefinition2 =
			_objectDefinitionLocalService.getObjectDefinition(
				objectRelationship.getObjectDefinitionId2());

		List<Long> ids = new ArrayList<>();

		try (PreparedStatement preparedStatement =
				CurrentConnectionUtil.getConnection(
					_objectEntryLocalService.getBasePersistence(
					).getDataSource()
				).prepareStatement(
					StringBundler.concat(
						"select ",
						objectDefinition2.getPKObjectFieldDBColumnName(),
						" from ", objectField.getDBTableName(), " where ",
						objectField.getDBColumnName(), " = ?")
				)) {

			preparedStatement.setLong(1, primaryKey);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					ids.add(
						resultSet.getLong(
							objectDefinition2.getPKObjectFieldDBColumnName()));
				}
			}
		}
		catch (SQLException e) {
			_log.debug(e);
		}

		System.out.println("SIze" +ids.size());

		if (Objects.equals(
				deletionType,
				ObjectRelationshipConstants.DELETION_TYPE_CASCADE)) {

			ActionableDynamicQuery actionableDynamicQuery =
				new DefaultActionableDynamicQuery() {

					@Override
					protected void actionsCompleted() throws PortalException {
						Session portletSession =
							_objectEntryLocalService.getBasePersistence(
							).openSession();

						portletSession.flush();

						portletSession.clear();

						Session portalSession =
							_resourcePermissionLocalService.getBasePersistence(
							).openSession();

						portalSession.flush();

						portalSession.clear();
					}

					@Override
					protected void intervalCompleted(
							long startPrimaryKey, long endPrimaryKey)
						throws PortalException {

						Session portletSession =
							_objectEntryLocalService.getBasePersistence(
							).openSession();

						portletSession.flush();

						portletSession.clear();

						Session portalSession =
							_resourcePermissionLocalService.getBasePersistence(
							).openSession();

						portalSession.flush();

						portalSession.clear();
					}

				};

			actionableDynamicQuery.setAddCriteriaMethod(
				dynamicQuery -> {
					Property nameProperty = PropertyFactoryUtil.forName(
						"objectEntryId");

					dynamicQuery.add(nameProperty.in(ids));
				});
			actionableDynamicQuery.setBaseLocalService(
				_objectEntryLocalService);

			Class<?> clazz = getClass();

			actionableDynamicQuery.setClassLoader(clazz.getClassLoader());

			actionableDynamicQuery.setModelClass(ObjectEntry.class);

			boolean skipObjectEntryResourcePermission =
				ObjectEntryThreadLocal.isSkipObjectEntryResourcePermission();

			actionableDynamicQuery.setPerformActionMethod(
				(ObjectEntry objectEntry) -> {
					ObjectEntryThreadLocal.setSkipObjectEntryResourcePermission(
						skipObjectEntryResourcePermission);

					_objectEntryLocalService.deleteObjectEntry(objectEntry);
				});

			actionableDynamicQuery.setPrimaryKeyPropertyName("objectEntryId");

			try {
				actionableDynamicQuery.performActions();
			}
			catch (Exception exception) {
				_log.debug(exception);
			}
		}

		if (Objects.equals(
				deletionType,
				ObjectRelationshipConstants.DELETION_TYPE_DISASSOCIATE)) {

			for (Long id : ids) {
				_objectEntryService.updateObjectEntry(
					id,
					HashMapBuilder.<String, Serializable>put(
						objectField.getName(), 0
					).build(),
					new ServiceContext());
			}
		}
		else if (Objects.equals(
					deletionType,
					ObjectRelationshipConstants.DELETION_TYPE_PREVENT)) {

			throw new RequiredObjectRelationshipException(objectRelationship);
		}
	}

	@Override
	public void disassociateRelatedModels(
			long userId, long objectRelationshipId, long primaryKey1,
			long primaryKey2)
		throws PortalException {

		ObjectEntry objectEntry = _objectEntryService.getObjectEntry(
			primaryKey2);

		_objectEntryService.updateObjectEntry(
			primaryKey2,
			HashMapBuilder.<String, Serializable>putAll(
				objectEntry.getValues()
			).put(
				() -> {
					ObjectRelationship objectRelationship =
						_objectRelationshipLocalService.getObjectRelationship(
							objectRelationshipId);

					ObjectField objectField =
						_objectFieldLocalService.getObjectField(
							objectRelationship.getObjectFieldId2());

					return objectField.getName();
				},
				0
			).build(),
			new ServiceContext());
	}

	@Override
	public ObjectEntry fetchRelatedModel(
			long groupId, long objectRelationshipId, long primaryKey)
		throws PortalException {

		return _objectEntryService.fetchManyToOneObjectEntry(
			groupId, objectRelationshipId, primaryKey);
	}

	@Override
	public String getClassName() {
		return _className;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getObjectRelationshipType() {
		return ObjectRelationshipConstants.TYPE_ONE_TO_MANY;
	}

	@Override
	public List<ObjectEntry> getRelatedModels(
			long groupId, long objectRelationshipId, long primaryKey,
			String search, int start, int end)
		throws PortalException {

		return _objectEntryService.getOneToManyObjectEntries(
			groupId, objectRelationshipId, primaryKey, true, search, start,
			end);
	}

	@Override
	public int getRelatedModelsCount(
			long groupId, long objectRelationshipId, long primaryKey,
			String search)
		throws PortalException {

		return _objectEntryService.getOneToManyObjectEntriesCount(
			groupId, objectRelationshipId, primaryKey, true, search);
	}

	@Override
	public List<ObjectEntry> getUnrelatedModels(
			long companyId, long groupId, ObjectDefinition objectDefinition,
			long objectEntryId, long objectRelationshipId, int start, int end)
		throws PortalException {

		return _objectEntryService.getOneToManyObjectEntries(
			groupId, objectRelationshipId, objectEntryId, false, null, start,
			end);
	}

	@Override
	public int getUnrelatedModelsCount(
			long companyId, long groupId, ObjectDefinition objectDefinition,
			long objectEntryId, long objectRelationshipId)
		throws PortalException {

		return _objectEntryService.getOneToManyObjectEntriesCount(
			groupId, objectRelationshipId, objectEntryId, false, null);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntry1toMObjectRelatedModelsProviderImpl.class);

	private final String _className;
	private final long _companyId;
	private final ObjectDefinitionLocalService _objectDefinitionLocalService;
	private final ObjectEntryLocalService _objectEntryLocalService;
	private final ObjectEntryService _objectEntryService;
	private final ObjectFieldLocalService _objectFieldLocalService;
	private final ObjectRelationshipLocalService
		_objectRelationshipLocalService;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;

}