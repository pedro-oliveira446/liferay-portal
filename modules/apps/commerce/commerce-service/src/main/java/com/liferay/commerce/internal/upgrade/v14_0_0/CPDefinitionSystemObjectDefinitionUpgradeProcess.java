/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v14_0_0;

import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcessFactory;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.Objects;

/**
 * @author Pedro Leite
 */
public class CPDefinitionSystemObjectDefinitionUpgradeProcess
	extends UpgradeProcess {

	public CPDefinitionSystemObjectDefinitionUpgradeProcess(
		CompanyLocalService companyLocalService,
		ObjectDefinitionLocalService objectDefinitionLocalService,
		ObjectFieldLocalService objectFieldLocalService,
		ObjectRelationshipLocalService objectRelationshipLocalService) {

		_companyLocalService = companyLocalService;
		_objectDefinitionLocalService = objectDefinitionLocalService;
		_objectFieldLocalService = objectFieldLocalService;
		_objectRelationshipLocalService = objectRelationshipLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_companyLocalService.forEachCompanyId(
			companyId -> {
				ObjectDefinition objectDefinition =
					_objectDefinitionLocalService.
						fetchObjectDefinitionByExternalReferenceCode(
							"L_COMMERCE_PRODUCT_DEFINITION", companyId);

				objectDefinition.setPKObjectFieldDBColumnName(
					_NEW_PK_OBJECT_FIELD_DB_COLUMN_NAME);
				objectDefinition.setPKObjectFieldName(
					_NEW_PK_OBJECT_FIELD_DB_COLUMN_NAME);

				objectDefinition =
					_objectDefinitionLocalService.updateObjectDefinition(
						objectDefinition);

				_updateObjectField(
					_NEW_PK_OBJECT_FIELD_DB_COLUMN_NAME,
					_objectFieldLocalService.getObjectField(
						objectDefinition.getObjectDefinitionId(), "id"));
				_updateObjectField(
					_NEW_PK_OBJECT_FIELD_DB_COLUMN_NAME,
					_objectFieldLocalService.getObjectField(
						objectDefinition.getObjectDefinitionId(), "productId"));

				if (hasTable(objectDefinition.getExtensionDBTableName())) {
					_updateDynamicObjectDefinitionTable(
						_OLD_PK_OBJECT_FIELD_DB_COLUMN_NAME,
						objectDefinition.getExtensionDBTableName(),
						objectDefinition);

					return;
				}

				if (hasTable(objectDefinition.getLocalizationDBTableName())) {
					_updateDynamicObjectDefinitionTable(
						_OLD_PK_OBJECT_FIELD_DB_COLUMN_NAME,
						objectDefinition.getLocalizationDBTableName(),
						objectDefinition);

					return;
				}

				List<ObjectRelationship> objectRelationships =
					_objectRelationshipLocalService.getObjectRelationships(
						objectDefinition.getObjectDefinitionId());

				for (ObjectRelationship objectRelationship :
						objectRelationships) {

					String dbColumnName = null;
					String dbTableName = null;

					if (Objects.equals(
							objectRelationship.getType(),
							ObjectRelationshipConstants.TYPE_ONE_TO_ONE) ||
						Objects.equals(
							objectRelationship.getType(),
							ObjectRelationshipConstants.TYPE_ONE_TO_MANY)) {

						ObjectField objectField2 =
							_objectFieldLocalService.getObjectField(
								objectRelationship.getObjectFieldId2());

						objectField2.setName(_replace(objectField2.getName()));

						_updateObjectField(
							_replace(objectField2.getDBColumnName()),
							objectField2);

						dbColumnName = objectField2.getDBColumnName();
						dbTableName = objectField2.getDBTableName();
					}
					else if (Objects.equals(
								objectRelationship.getType(),
								ObjectRelationshipConstants.
									TYPE_MANY_TO_MANY) &&
							 !objectRelationship.isReverse()) {

						dbColumnName = _OLD_PK_OBJECT_FIELD_DB_COLUMN_NAME;
						dbTableName = objectRelationship.getDBTableName();
					}

					_updateDynamicObjectDefinitionTable(
						dbColumnName, dbTableName, objectDefinition);
				}
			});
	}

	private String _replace(String name) {
		return StringUtil.replace(
			name, _OLD_PK_OBJECT_FIELD_DB_COLUMN_NAME,
			_NEW_PK_OBJECT_FIELD_DB_COLUMN_NAME);
	}

	private void _updateDynamicObjectDefinitionTable(
			String dbColumnName, String dbTableName,
			ObjectDefinition objectDefinition)
		throws Exception {

		runSQL(
			StringBundler.concat(
				"UPDATE ", dbTableName, " SET ", dbColumnName, " = (SELECT ",
				_NEW_PK_OBJECT_FIELD_DB_COLUMN_NAME, " FROM ",
				objectDefinition.getDBTableName(), " WHERE ",
				_OLD_PK_OBJECT_FIELD_DB_COLUMN_NAME, " = ", dbTableName, ".",
				dbColumnName, ")"));

		UpgradeProcessFactory.alterColumnName(
			dbTableName, dbColumnName, _replace(dbColumnName) + " LONG");
	}

	private void _updateObjectField(
		String dbColumnName, ObjectField objectField) {

		objectField.setDBColumnName(dbColumnName);

		_objectFieldLocalService.updateObjectField(objectField);
	}

	private static final String _NEW_PK_OBJECT_FIELD_DB_COLUMN_NAME =
		"CProductId";

	private static final String _OLD_PK_OBJECT_FIELD_DB_COLUMN_NAME =
		"CPDefinitionId";

	private final CompanyLocalService _companyLocalService;
	private final ObjectDefinitionLocalService _objectDefinitionLocalService;
	private final ObjectFieldLocalService _objectFieldLocalService;
	private final ObjectRelationshipLocalService
		_objectRelationshipLocalService;

}