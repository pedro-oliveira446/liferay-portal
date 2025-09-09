/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.service.persistence;

import com.liferay.object.model.ObjectEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the object entry service. This utility wraps <code>com.liferay.object.service.persistence.impl.ObjectEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @see ObjectEntryPersistence
 * @generated
 */
public class ObjectEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ObjectEntry objectEntry) {
		getPersistence().clearCache(objectEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ObjectEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ObjectEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ObjectEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ObjectEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ObjectEntry update(ObjectEntry objectEntry) {
		return getPersistence().update(objectEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ObjectEntry update(
		ObjectEntry objectEntry, ServiceContext serviceContext) {

		return getPersistence().update(objectEntry, serviceContext);
	}

	/**
	 * Returns all the object entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the object entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByUuid_First(
			String uuid, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByUuid_First(
		String uuid, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByUuid_Last(
			String uuid, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByUuid_Last(
		String uuid, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where uuid = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByUuid_PrevAndNext(
			long objectEntryId, String uuid,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByUuid_PrevAndNext(
			objectEntryId, uuid, orderByComparator);
	}

	/**
	 * Removes all the object entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of object entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching object entries
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the object entry where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchObjectEntryException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByUUID_G(String uuid, long groupId)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the object entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the object entry where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the object entry where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the object entry that was removed
	 */
	public static ObjectEntry removeByUUID_G(String uuid, long groupId)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of object entries where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching object entries
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the object entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the object entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByUuid_C_PrevAndNext(
			long objectEntryId, String uuid, long companyId,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByUuid_C_PrevAndNext(
			objectEntryId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the object entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of object entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching object entries
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the object entry where parentObjectEntryId = &#63; or throws a <code>NoSuchObjectEntryException</code> if it could not be found.
	 *
	 * @param parentObjectEntryId the parent object entry ID
	 * @return the matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByParentObjectEntryId(
			long parentObjectEntryId)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByParentObjectEntryId(parentObjectEntryId);
	}

	/**
	 * Returns the object entry where parentObjectEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param parentObjectEntryId the parent object entry ID
	 * @return the matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByParentObjectEntryId(
		long parentObjectEntryId) {

		return getPersistence().fetchByParentObjectEntryId(parentObjectEntryId);
	}

	/**
	 * Returns the object entry where parentObjectEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param parentObjectEntryId the parent object entry ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByParentObjectEntryId(
		long parentObjectEntryId, boolean useFinderCache) {

		return getPersistence().fetchByParentObjectEntryId(
			parentObjectEntryId, useFinderCache);
	}

	/**
	 * Removes the object entry where parentObjectEntryId = &#63; from the database.
	 *
	 * @param parentObjectEntryId the parent object entry ID
	 * @return the object entry that was removed
	 */
	public static ObjectEntry removeByParentObjectEntryId(
			long parentObjectEntryId)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().removeByParentObjectEntryId(
			parentObjectEntryId);
	}

	/**
	 * Returns the number of object entries where parentObjectEntryId = &#63;.
	 *
	 * @param parentObjectEntryId the parent object entry ID
	 * @return the number of matching object entries
	 */
	public static int countByParentObjectEntryId(long parentObjectEntryId) {
		return getPersistence().countByParentObjectEntryId(parentObjectEntryId);
	}

	/**
	 * Returns all the object entries where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByODI_L(
		long objectDefinitionId, boolean latest) {

		return getPersistence().findByODI_L(objectDefinitionId, latest);
	}

	/**
	 * Returns a range of all the object entries where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByODI_L(
		long objectDefinitionId, boolean latest, int start, int end) {

		return getPersistence().findByODI_L(
			objectDefinitionId, latest, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByODI_L(
		long objectDefinitionId, boolean latest, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByODI_L(
			objectDefinitionId, latest, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByODI_L(
		long objectDefinitionId, boolean latest, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByODI_L(
			objectDefinitionId, latest, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByODI_L_First(
			long objectDefinitionId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByODI_L_First(
			objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByODI_L_First(
		long objectDefinitionId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByODI_L_First(
			objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByODI_L_Last(
			long objectDefinitionId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByODI_L_Last(
			objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByODI_L_Last(
		long objectDefinitionId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByODI_L_Last(
			objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByODI_L_PrevAndNext(
			long objectEntryId, long objectDefinitionId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByODI_L_PrevAndNext(
			objectEntryId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Removes all the object entries where objectDefinitionId = &#63; and latest = &#63; from the database.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 */
	public static void removeByODI_L(long objectDefinitionId, boolean latest) {
		getPersistence().removeByODI_L(objectDefinitionId, latest);
	}

	/**
	 * Returns the number of object entries where objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the number of matching object entries
	 */
	public static int countByODI_L(long objectDefinitionId, boolean latest) {
		return getPersistence().countByODI_L(objectDefinitionId, latest);
	}

	/**
	 * Returns all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByG_ODI_L(
		long groupId, long objectDefinitionId, boolean latest) {

		return getPersistence().findByG_ODI_L(
			groupId, objectDefinitionId, latest);
	}

	/**
	 * Returns a range of all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByG_ODI_L(
		long groupId, long objectDefinitionId, boolean latest, int start,
		int end) {

		return getPersistence().findByG_ODI_L(
			groupId, objectDefinitionId, latest, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByG_ODI_L(
		long groupId, long objectDefinitionId, boolean latest, int start,
		int end, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByG_ODI_L(
			groupId, objectDefinitionId, latest, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByG_ODI_L(
		long groupId, long objectDefinitionId, boolean latest, int start,
		int end, OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_ODI_L(
			groupId, objectDefinitionId, latest, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByG_ODI_L_First(
			long groupId, long objectDefinitionId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_ODI_L_First(
			groupId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByG_ODI_L_First(
		long groupId, long objectDefinitionId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByG_ODI_L_First(
			groupId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByG_ODI_L_Last(
			long groupId, long objectDefinitionId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_ODI_L_Last(
			groupId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByG_ODI_L_Last(
		long groupId, long objectDefinitionId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByG_ODI_L_Last(
			groupId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByG_ODI_L_PrevAndNext(
			long objectEntryId, long groupId, long objectDefinitionId,
			boolean latest, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_ODI_L_PrevAndNext(
			objectEntryId, groupId, objectDefinitionId, latest,
			orderByComparator);
	}

	/**
	 * Removes all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 */
	public static void removeByG_ODI_L(
		long groupId, long objectDefinitionId, boolean latest) {

		getPersistence().removeByG_ODI_L(groupId, objectDefinitionId, latest);
	}

	/**
	 * Returns the number of object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the number of matching object entries
	 */
	public static int countByG_ODI_L(
		long groupId, long objectDefinitionId, boolean latest) {

		return getPersistence().countByG_ODI_L(
			groupId, objectDefinitionId, latest);
	}

	/**
	 * Returns all the object entries where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByG_OEFI_L(
		long groupId, long objectEntryFolderId, boolean latest) {

		return getPersistence().findByG_OEFI_L(
			groupId, objectEntryFolderId, latest);
	}

	/**
	 * Returns a range of all the object entries where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByG_OEFI_L(
		long groupId, long objectEntryFolderId, boolean latest, int start,
		int end) {

		return getPersistence().findByG_OEFI_L(
			groupId, objectEntryFolderId, latest, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByG_OEFI_L(
		long groupId, long objectEntryFolderId, boolean latest, int start,
		int end, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByG_OEFI_L(
			groupId, objectEntryFolderId, latest, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByG_OEFI_L(
		long groupId, long objectEntryFolderId, boolean latest, int start,
		int end, OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_OEFI_L(
			groupId, objectEntryFolderId, latest, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByG_OEFI_L_First(
			long groupId, long objectEntryFolderId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_OEFI_L_First(
			groupId, objectEntryFolderId, latest, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByG_OEFI_L_First(
		long groupId, long objectEntryFolderId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByG_OEFI_L_First(
			groupId, objectEntryFolderId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByG_OEFI_L_Last(
			long groupId, long objectEntryFolderId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_OEFI_L_Last(
			groupId, objectEntryFolderId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByG_OEFI_L_Last(
		long groupId, long objectEntryFolderId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByG_OEFI_L_Last(
			groupId, objectEntryFolderId, latest, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByG_OEFI_L_PrevAndNext(
			long objectEntryId, long groupId, long objectEntryFolderId,
			boolean latest, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_OEFI_L_PrevAndNext(
			objectEntryId, groupId, objectEntryFolderId, latest,
			orderByComparator);
	}

	/**
	 * Removes all the object entries where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 */
	public static void removeByG_OEFI_L(
		long groupId, long objectEntryFolderId, boolean latest) {

		getPersistence().removeByG_OEFI_L(groupId, objectEntryFolderId, latest);
	}

	/**
	 * Returns the number of object entries where groupId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @return the number of matching object entries
	 */
	public static int countByG_OEFI_L(
		long groupId, long objectEntryFolderId, boolean latest) {

		return getPersistence().countByG_OEFI_L(
			groupId, objectEntryFolderId, latest);
	}

	/**
	 * Returns all the object entries where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByU_ODI_L(
		long userId, long objectDefinitionId, boolean latest) {

		return getPersistence().findByU_ODI_L(
			userId, objectDefinitionId, latest);
	}

	/**
	 * Returns a range of all the object entries where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByU_ODI_L(
		long userId, long objectDefinitionId, boolean latest, int start,
		int end) {

		return getPersistence().findByU_ODI_L(
			userId, objectDefinitionId, latest, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByU_ODI_L(
		long userId, long objectDefinitionId, boolean latest, int start,
		int end, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByU_ODI_L(
			userId, objectDefinitionId, latest, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByU_ODI_L(
		long userId, long objectDefinitionId, boolean latest, int start,
		int end, OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_ODI_L(
			userId, objectDefinitionId, latest, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByU_ODI_L_First(
			long userId, long objectDefinitionId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByU_ODI_L_First(
			userId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByU_ODI_L_First(
		long userId, long objectDefinitionId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByU_ODI_L_First(
			userId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByU_ODI_L_Last(
			long userId, long objectDefinitionId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByU_ODI_L_Last(
			userId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByU_ODI_L_Last(
		long userId, long objectDefinitionId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByU_ODI_L_Last(
			userId, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByU_ODI_L_PrevAndNext(
			long objectEntryId, long userId, long objectDefinitionId,
			boolean latest, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByU_ODI_L_PrevAndNext(
			objectEntryId, userId, objectDefinitionId, latest,
			orderByComparator);
	}

	/**
	 * Removes all the object entries where userId = &#63; and objectDefinitionId = &#63; and latest = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 */
	public static void removeByU_ODI_L(
		long userId, long objectDefinitionId, boolean latest) {

		getPersistence().removeByU_ODI_L(userId, objectDefinitionId, latest);
	}

	/**
	 * Returns the number of object entries where userId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the number of matching object entries
	 */
	public static int countByU_ODI_L(
		long userId, long objectDefinitionId, boolean latest) {

		return getPersistence().countByU_ODI_L(
			userId, objectDefinitionId, latest);
	}

	/**
	 * Returns all the object entries where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByODI_L_NotS(
		long objectDefinitionId, boolean latest, int status) {

		return getPersistence().findByODI_L_NotS(
			objectDefinitionId, latest, status);
	}

	/**
	 * Returns a range of all the object entries where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByODI_L_NotS(
		long objectDefinitionId, boolean latest, int status, int start,
		int end) {

		return getPersistence().findByODI_L_NotS(
			objectDefinitionId, latest, status, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByODI_L_NotS(
		long objectDefinitionId, boolean latest, int status, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByODI_L_NotS(
			objectDefinitionId, latest, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByODI_L_NotS(
		long objectDefinitionId, boolean latest, int status, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByODI_L_NotS(
			objectDefinitionId, latest, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByODI_L_NotS_First(
			long objectDefinitionId, boolean latest, int status,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByODI_L_NotS_First(
			objectDefinitionId, latest, status, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByODI_L_NotS_First(
		long objectDefinitionId, boolean latest, int status,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByODI_L_NotS_First(
			objectDefinitionId, latest, status, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByODI_L_NotS_Last(
			long objectDefinitionId, boolean latest, int status,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByODI_L_NotS_Last(
			objectDefinitionId, latest, status, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByODI_L_NotS_Last(
		long objectDefinitionId, boolean latest, int status,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByODI_L_NotS_Last(
			objectDefinitionId, latest, status, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByODI_L_NotS_PrevAndNext(
			long objectEntryId, long objectDefinitionId, boolean latest,
			int status, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByODI_L_NotS_PrevAndNext(
			objectEntryId, objectDefinitionId, latest, status,
			orderByComparator);
	}

	/**
	 * Removes all the object entries where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63; from the database.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 */
	public static void removeByODI_L_NotS(
		long objectDefinitionId, boolean latest, int status) {

		getPersistence().removeByODI_L_NotS(objectDefinitionId, latest, status);
	}

	/**
	 * Returns the number of object entries where objectDefinitionId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @return the number of matching object entries
	 */
	public static int countByODI_L_NotS(
		long objectDefinitionId, boolean latest, int status) {

		return getPersistence().countByODI_L_NotS(
			objectDefinitionId, latest, status);
	}

	/**
	 * Returns all the object entries where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByROEI_L_NotS(
		long rootObjectEntryId, boolean latest, int status) {

		return getPersistence().findByROEI_L_NotS(
			rootObjectEntryId, latest, status);
	}

	/**
	 * Returns a range of all the object entries where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByROEI_L_NotS(
		long rootObjectEntryId, boolean latest, int status, int start,
		int end) {

		return getPersistence().findByROEI_L_NotS(
			rootObjectEntryId, latest, status, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByROEI_L_NotS(
		long rootObjectEntryId, boolean latest, int status, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByROEI_L_NotS(
			rootObjectEntryId, latest, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByROEI_L_NotS(
		long rootObjectEntryId, boolean latest, int status, int start, int end,
		OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByROEI_L_NotS(
			rootObjectEntryId, latest, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByROEI_L_NotS_First(
			long rootObjectEntryId, boolean latest, int status,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByROEI_L_NotS_First(
			rootObjectEntryId, latest, status, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByROEI_L_NotS_First(
		long rootObjectEntryId, boolean latest, int status,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByROEI_L_NotS_First(
			rootObjectEntryId, latest, status, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByROEI_L_NotS_Last(
			long rootObjectEntryId, boolean latest, int status,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByROEI_L_NotS_Last(
			rootObjectEntryId, latest, status, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByROEI_L_NotS_Last(
		long rootObjectEntryId, boolean latest, int status,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByROEI_L_NotS_Last(
			rootObjectEntryId, latest, status, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByROEI_L_NotS_PrevAndNext(
			long objectEntryId, long rootObjectEntryId, boolean latest,
			int status, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByROEI_L_NotS_PrevAndNext(
			objectEntryId, rootObjectEntryId, latest, status,
			orderByComparator);
	}

	/**
	 * Removes all the object entries where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63; from the database.
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 */
	public static void removeByROEI_L_NotS(
		long rootObjectEntryId, boolean latest, int status) {

		getPersistence().removeByROEI_L_NotS(rootObjectEntryId, latest, status);
	}

	/**
	 * Returns the number of object entries where rootObjectEntryId = &#63; and latest = &#63; and status &ne; &#63;.
	 *
	 * @param rootObjectEntryId the root object entry ID
	 * @param latest the latest
	 * @param status the status
	 * @return the number of matching object entries
	 */
	public static int countByROEI_L_NotS(
		long rootObjectEntryId, boolean latest, int status) {

		return getPersistence().countByROEI_L_NotS(
			rootObjectEntryId, latest, status);
	}

	/**
	 * Returns all the object entries where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByG_C_OEFI_L(
		long groupId, long companyId, long objectEntryFolderId,
		boolean latest) {

		return getPersistence().findByG_C_OEFI_L(
			groupId, companyId, objectEntryFolderId, latest);
	}

	/**
	 * Returns a range of all the object entries where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByG_C_OEFI_L(
		long groupId, long companyId, long objectEntryFolderId, boolean latest,
		int start, int end) {

		return getPersistence().findByG_C_OEFI_L(
			groupId, companyId, objectEntryFolderId, latest, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByG_C_OEFI_L(
		long groupId, long companyId, long objectEntryFolderId, boolean latest,
		int start, int end, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByG_C_OEFI_L(
			groupId, companyId, objectEntryFolderId, latest, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByG_C_OEFI_L(
		long groupId, long companyId, long objectEntryFolderId, boolean latest,
		int start, int end, OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_C_OEFI_L(
			groupId, companyId, objectEntryFolderId, latest, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByG_C_OEFI_L_First(
			long groupId, long companyId, long objectEntryFolderId,
			boolean latest, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_C_OEFI_L_First(
			groupId, companyId, objectEntryFolderId, latest, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByG_C_OEFI_L_First(
		long groupId, long companyId, long objectEntryFolderId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByG_C_OEFI_L_First(
			groupId, companyId, objectEntryFolderId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByG_C_OEFI_L_Last(
			long groupId, long companyId, long objectEntryFolderId,
			boolean latest, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_C_OEFI_L_Last(
			groupId, companyId, objectEntryFolderId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByG_C_OEFI_L_Last(
		long groupId, long companyId, long objectEntryFolderId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByG_C_OEFI_L_Last(
			groupId, companyId, objectEntryFolderId, latest, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByG_C_OEFI_L_PrevAndNext(
			long objectEntryId, long groupId, long companyId,
			long objectEntryFolderId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_C_OEFI_L_PrevAndNext(
			objectEntryId, groupId, companyId, objectEntryFolderId, latest,
			orderByComparator);
	}

	/**
	 * Removes all the object entries where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 */
	public static void removeByG_C_OEFI_L(
		long groupId, long companyId, long objectEntryFolderId,
		boolean latest) {

		getPersistence().removeByG_C_OEFI_L(
			groupId, companyId, objectEntryFolderId, latest);
	}

	/**
	 * Returns the number of object entries where groupId = &#63; and companyId = &#63; and objectEntryFolderId = &#63; and latest = &#63;.
	 *
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectEntryFolderId the object entry folder ID
	 * @param latest the latest
	 * @return the number of matching object entries
	 */
	public static int countByG_C_OEFI_L(
		long groupId, long companyId, long objectEntryFolderId,
		boolean latest) {

		return getPersistence().countByG_C_OEFI_L(
			groupId, companyId, objectEntryFolderId, latest);
	}

	/**
	 * Returns all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByG_ODI_L_S(
		long groupId, long objectDefinitionId, boolean latest, int status) {

		return getPersistence().findByG_ODI_L_S(
			groupId, objectDefinitionId, latest, status);
	}

	/**
	 * Returns a range of all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByG_ODI_L_S(
		long groupId, long objectDefinitionId, boolean latest, int status,
		int start, int end) {

		return getPersistence().findByG_ODI_L_S(
			groupId, objectDefinitionId, latest, status, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByG_ODI_L_S(
		long groupId, long objectDefinitionId, boolean latest, int status,
		int start, int end, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByG_ODI_L_S(
			groupId, objectDefinitionId, latest, status, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByG_ODI_L_S(
		long groupId, long objectDefinitionId, boolean latest, int status,
		int start, int end, OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_ODI_L_S(
			groupId, objectDefinitionId, latest, status, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByG_ODI_L_S_First(
			long groupId, long objectDefinitionId, boolean latest, int status,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_ODI_L_S_First(
			groupId, objectDefinitionId, latest, status, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByG_ODI_L_S_First(
		long groupId, long objectDefinitionId, boolean latest, int status,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByG_ODI_L_S_First(
			groupId, objectDefinitionId, latest, status, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByG_ODI_L_S_Last(
			long groupId, long objectDefinitionId, boolean latest, int status,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_ODI_L_S_Last(
			groupId, objectDefinitionId, latest, status, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByG_ODI_L_S_Last(
		long groupId, long objectDefinitionId, boolean latest, int status,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByG_ODI_L_S_Last(
			groupId, objectDefinitionId, latest, status, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByG_ODI_L_S_PrevAndNext(
			long objectEntryId, long groupId, long objectDefinitionId,
			boolean latest, int status,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByG_ODI_L_S_PrevAndNext(
			objectEntryId, groupId, objectDefinitionId, latest, status,
			orderByComparator);
	}

	/**
	 * Removes all the object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 */
	public static void removeByG_ODI_L_S(
		long groupId, long objectDefinitionId, boolean latest, int status) {

		getPersistence().removeByG_ODI_L_S(
			groupId, objectDefinitionId, latest, status);
	}

	/**
	 * Returns the number of object entries where groupId = &#63; and objectDefinitionId = &#63; and latest = &#63; and status = &#63;.
	 *
	 * @param groupId the group ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param status the status
	 * @return the number of matching object entries
	 */
	public static int countByG_ODI_L_S(
		long groupId, long objectDefinitionId, boolean latest, int status) {

		return getPersistence().countByG_ODI_L_S(
			groupId, objectDefinitionId, latest, status);
	}

	/**
	 * Returns all the object entries where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the matching object entries
	 */
	public static List<ObjectEntry> findByU_GtCD_ODI_L(
		long userId, Date createDate, long objectDefinitionId, boolean latest) {

		return getPersistence().findByU_GtCD_ODI_L(
			userId, createDate, objectDefinitionId, latest);
	}

	/**
	 * Returns a range of all the object entries where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of matching object entries
	 */
	public static List<ObjectEntry> findByU_GtCD_ODI_L(
		long userId, Date createDate, long objectDefinitionId, boolean latest,
		int start, int end) {

		return getPersistence().findByU_GtCD_ODI_L(
			userId, createDate, objectDefinitionId, latest, start, end);
	}

	/**
	 * Returns an ordered range of all the object entries where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByU_GtCD_ODI_L(
		long userId, Date createDate, long objectDefinitionId, boolean latest,
		int start, int end, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findByU_GtCD_ODI_L(
			userId, createDate, objectDefinitionId, latest, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching object entries
	 */
	public static List<ObjectEntry> findByU_GtCD_ODI_L(
		long userId, Date createDate, long objectDefinitionId, boolean latest,
		int start, int end, OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_GtCD_ODI_L(
			userId, createDate, objectDefinitionId, latest, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first object entry in the ordered set where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByU_GtCD_ODI_L_First(
			long userId, Date createDate, long objectDefinitionId,
			boolean latest, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByU_GtCD_ODI_L_First(
			userId, createDate, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the first object entry in the ordered set where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByU_GtCD_ODI_L_First(
		long userId, Date createDate, long objectDefinitionId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByU_GtCD_ODI_L_First(
			userId, createDate, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByU_GtCD_ODI_L_Last(
			long userId, Date createDate, long objectDefinitionId,
			boolean latest, OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByU_GtCD_ODI_L_Last(
			userId, createDate, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the last object entry in the ordered set where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByU_GtCD_ODI_L_Last(
		long userId, Date createDate, long objectDefinitionId, boolean latest,
		OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().fetchByU_GtCD_ODI_L_Last(
			userId, createDate, objectDefinitionId, latest, orderByComparator);
	}

	/**
	 * Returns the object entries before and after the current object entry in the ordered set where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param objectEntryId the primary key of the current object entry
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry[] findByU_GtCD_ODI_L_PrevAndNext(
			long objectEntryId, long userId, Date createDate,
			long objectDefinitionId, boolean latest,
			OrderByComparator<ObjectEntry> orderByComparator)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByU_GtCD_ODI_L_PrevAndNext(
			objectEntryId, userId, createDate, objectDefinitionId, latest,
			orderByComparator);
	}

	/**
	 * Removes all the object entries where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 */
	public static void removeByU_GtCD_ODI_L(
		long userId, Date createDate, long objectDefinitionId, boolean latest) {

		getPersistence().removeByU_GtCD_ODI_L(
			userId, createDate, objectDefinitionId, latest);
	}

	/**
	 * Returns the number of object entries where userId = &#63; and createDate &gt; &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param userId the user ID
	 * @param createDate the create date
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the number of matching object entries
	 */
	public static int countByU_GtCD_ODI_L(
		long userId, Date createDate, long objectDefinitionId, boolean latest) {

		return getPersistence().countByU_GtCD_ODI_L(
			userId, createDate, objectDefinitionId, latest);
	}

	/**
	 * Returns the object entry where externalReferenceCode = &#63; and groupId = &#63; and companyId = &#63; and objectDefinitionId = &#63; and latest = &#63; or throws a <code>NoSuchObjectEntryException</code> if it could not be found.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the matching object entry
	 * @throws NoSuchObjectEntryException if a matching object entry could not be found
	 */
	public static ObjectEntry findByERC_G_C_ODI_L(
			String externalReferenceCode, long groupId, long companyId,
			long objectDefinitionId, boolean latest)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByERC_G_C_ODI_L(
			externalReferenceCode, groupId, companyId, objectDefinitionId,
			latest);
	}

	/**
	 * Returns the object entry where externalReferenceCode = &#63; and groupId = &#63; and companyId = &#63; and objectDefinitionId = &#63; and latest = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByERC_G_C_ODI_L(
		String externalReferenceCode, long groupId, long companyId,
		long objectDefinitionId, boolean latest) {

		return getPersistence().fetchByERC_G_C_ODI_L(
			externalReferenceCode, groupId, companyId, objectDefinitionId,
			latest);
	}

	/**
	 * Returns the object entry where externalReferenceCode = &#63; and groupId = &#63; and companyId = &#63; and objectDefinitionId = &#63; and latest = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching object entry, or <code>null</code> if a matching object entry could not be found
	 */
	public static ObjectEntry fetchByERC_G_C_ODI_L(
		String externalReferenceCode, long groupId, long companyId,
		long objectDefinitionId, boolean latest, boolean useFinderCache) {

		return getPersistence().fetchByERC_G_C_ODI_L(
			externalReferenceCode, groupId, companyId, objectDefinitionId,
			latest, useFinderCache);
	}

	/**
	 * Removes the object entry where externalReferenceCode = &#63; and groupId = &#63; and companyId = &#63; and objectDefinitionId = &#63; and latest = &#63; from the database.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the object entry that was removed
	 */
	public static ObjectEntry removeByERC_G_C_ODI_L(
			String externalReferenceCode, long groupId, long companyId,
			long objectDefinitionId, boolean latest)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().removeByERC_G_C_ODI_L(
			externalReferenceCode, groupId, companyId, objectDefinitionId,
			latest);
	}

	/**
	 * Returns the number of object entries where externalReferenceCode = &#63; and groupId = &#63; and companyId = &#63; and objectDefinitionId = &#63; and latest = &#63;.
	 *
	 * @param externalReferenceCode the external reference code
	 * @param groupId the group ID
	 * @param companyId the company ID
	 * @param objectDefinitionId the object definition ID
	 * @param latest the latest
	 * @return the number of matching object entries
	 */
	public static int countByERC_G_C_ODI_L(
		String externalReferenceCode, long groupId, long companyId,
		long objectDefinitionId, boolean latest) {

		return getPersistence().countByERC_G_C_ODI_L(
			externalReferenceCode, groupId, companyId, objectDefinitionId,
			latest);
	}

	/**
	 * Caches the object entry in the entity cache if it is enabled.
	 *
	 * @param objectEntry the object entry
	 */
	public static void cacheResult(ObjectEntry objectEntry) {
		getPersistence().cacheResult(objectEntry);
	}

	/**
	 * Caches the object entries in the entity cache if it is enabled.
	 *
	 * @param objectEntries the object entries
	 */
	public static void cacheResult(List<ObjectEntry> objectEntries) {
		getPersistence().cacheResult(objectEntries);
	}

	/**
	 * Creates a new object entry with the primary key. Does not add the object entry to the database.
	 *
	 * @param objectEntryId the primary key for the new object entry
	 * @return the new object entry
	 */
	public static ObjectEntry create(long objectEntryId) {
		return getPersistence().create(objectEntryId);
	}

	/**
	 * Removes the object entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param objectEntryId the primary key of the object entry
	 * @return the object entry that was removed
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry remove(long objectEntryId)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().remove(objectEntryId);
	}

	public static ObjectEntry updateImpl(ObjectEntry objectEntry) {
		return getPersistence().updateImpl(objectEntry);
	}

	/**
	 * Returns the object entry with the primary key or throws a <code>NoSuchObjectEntryException</code> if it could not be found.
	 *
	 * @param objectEntryId the primary key of the object entry
	 * @return the object entry
	 * @throws NoSuchObjectEntryException if a object entry with the primary key could not be found
	 */
	public static ObjectEntry findByPrimaryKey(long objectEntryId)
		throws com.liferay.object.exception.NoSuchObjectEntryException {

		return getPersistence().findByPrimaryKey(objectEntryId);
	}

	/**
	 * Returns the object entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param objectEntryId the primary key of the object entry
	 * @return the object entry, or <code>null</code> if a object entry with the primary key could not be found
	 */
	public static ObjectEntry fetchByPrimaryKey(long objectEntryId) {
		return getPersistence().fetchByPrimaryKey(objectEntryId);
	}

	/**
	 * Returns all the object entries.
	 *
	 * @return the object entries
	 */
	public static List<ObjectEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the object entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @return the range of object entries
	 */
	public static List<ObjectEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the object entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of object entries
	 */
	public static List<ObjectEntry> findAll(
		int start, int end, OrderByComparator<ObjectEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the object entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ObjectEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of object entries
	 * @param end the upper bound of the range of object entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of object entries
	 */
	public static List<ObjectEntry> findAll(
		int start, int end, OrderByComparator<ObjectEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the object entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of object entries.
	 *
	 * @return the number of object entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ObjectEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ObjectEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ObjectEntryPersistence _persistence;

}