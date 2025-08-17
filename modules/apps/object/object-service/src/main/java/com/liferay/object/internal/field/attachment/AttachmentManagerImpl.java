/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.internal.field.attachment;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.exception.NoSuchFolderException;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.service.DLFolderService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.object.configuration.ObjectConfiguration;
import com.liferay.object.constants.ObjectFieldSettingConstants;
import com.liferay.object.field.attachment.AttachmentManager;
import com.liferay.object.field.setting.util.ObjectFieldSettingUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectFieldSetting;
import com.liferay.object.service.ObjectFieldSettingLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upload.configuration.UploadServletRequestConfigurationProvider;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypes;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.documentlibrary.util.DLAppUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carolina Barbosa
 * @author Carlos Correa
 */
@Component(
	configurationPid = "com.liferay.object.configuration.ObjectConfiguration",
	service = AttachmentManager.class
)
public class AttachmentManagerImpl implements AttachmentManager {

	@Override
	public void deleteFileEntries(
		List<ObjectField> objectFields,
		Supplier<Map<String, Serializable>> valuesSupplier) {

		Map<String, Serializable> values = null;

		for (ObjectField objectField : objectFields) {
			if (objectField.isSystem() || !isFileEntryDeletable(objectField)) {
				continue;
			}

			if (values == null) {
				values = valuesSupplier.get();
			}

			List<Long> orphanedFileEntryIds = new ArrayList<>();

			if (objectField.isLocalized()) {
				Map<String, Serializable> localizedValues =
					(Map<String, Serializable>)values.get(
						objectField.getI18nObjectFieldName());

				if (localizedValues == null) {
					continue;
				}

				for (Map.Entry<String, Serializable> entry :
						localizedValues.entrySet()) {

					orphanedFileEntryIds.add(_getFileEntryId(entry.getValue()));
				}
			}
			else {
				orphanedFileEntryIds.add(
					_getFileEntryId(values.get(objectField.getName())));
			}

			try {
				for (Long orphanedFileEntryId : orphanedFileEntryIds) {
					if (orphanedFileEntryId == 0) {
						continue;
					}

					_dlFileEntryLocalService.deleteFileEntry(
						orphanedFileEntryId);
				}
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}
		}
	}

	@Override
	public String[] getAcceptedFileExtensions(long objectFieldId) {
		ObjectFieldSetting objectFieldSetting =
			_objectFieldSettingLocalService.fetchObjectFieldSetting(
				objectFieldId,
				ObjectFieldSettingConstants.NAME_ACCEPTED_FILE_EXTENSIONS);

		String value = objectFieldSetting.getValue();

		return value.split("\\s*,\\s*");
	}

	@Override
	public DLFolder getDLFolder(
			long companyId, long groupId, ObjectField objectField,
			ServiceContext serviceContext, long userId)
		throws PortalException {

		Long dlFolderId = null;

		boolean showFilesInDocumentsAndMedia = GetterUtil.getBoolean(
			ObjectFieldSettingUtil.getValue(
				ObjectFieldSettingConstants.NAME_SHOW_FILES_IN_DOCS_AND_MEDIA,
				objectField.getObjectFieldSettings()));

		if (showFilesInDocumentsAndMedia) {
			String storageDLFolderPath = ObjectFieldSettingUtil.getValue(
				ObjectFieldSettingConstants.NAME_STORAGE_DL_FOLDER_PATH,
				objectField.getObjectFieldSettings());

			dlFolderId = _getStorageDLFolderId(
				companyId, groupId, serviceContext, storageDLFolderPath);
		}
		else {
			ObjectDefinition objectDefinition =
				objectField.getObjectDefinition();

			dlFolderId = _getRepositoryFolderId(
				companyId, groupId, objectDefinition.getPortletId(),
				serviceContext, userId);
		}

		return _dlFolderLocalService.getDLFolder(dlFolderId);
	}

	@Override
	public long getMaximumFileSize(long objectFieldId, boolean signedIn) {
		long maximumFileSize = Math.min(
			_getObjectFieldSettingMaximumFileSize(objectFieldId),
			_uploadServletRequestConfigurationProvider.getMaxSize());

		if (signedIn) {
			return maximumFileSize;
		}

		return Math.min(
			maximumFileSize,
			_objectConfiguration.maximumFileSizeForGuestUsers() *
				_FILE_LENGTH_MB);
	}

	@Override
	public FileEntry getOrAddFileEntry(
			long companyId, String externalReferenceCode, byte[] fileContent,
			String fileName, long groupId, ObjectField objectField,
			ServiceContext serviceContext)
		throws Exception {

		FileEntry fileEntry =
			_dlAppLocalService.fetchFileEntryByExternalReferenceCode(
				groupId, externalReferenceCode);

		if ((fileEntry != null) && (companyId == fileEntry.getCompanyId())) {
			return fileEntry;
		}

		_validateObjectDefinitionSettings(
			fileContent, fileName, objectField.getObjectFieldId(),
			serviceContext.getUserId());

		DLFolder dlFolder = getDLFolder(
			companyId, groupId, objectField, serviceContext,
			serviceContext.getUserId());

		try (InputStream inputStream = new ByteArrayInputStream(fileContent)) {
			String title = DLUtil.getUniqueTitle(
				groupId, dlFolder.getFolderId(),
				FileUtil.stripExtension(fileName));
			String sourceFileName = DLUtil.getUniqueFileName(
				groupId, dlFolder.getFolderId(), fileName, true);
			String mimeType = _mimeTypes.getContentType(inputStream, fileName);

			_validateDLSettings(
				companyId, groupId,
				DLAppUtil.getExtension(title, sourceFileName), mimeType,
				fileContent.length, sourceFileName);

			return _dlAppLocalService.addFileEntry(
				externalReferenceCode, serviceContext.getUserId(),
				dlFolder.getRepositoryId(), dlFolder.getFolderId(),
				sourceFileName, mimeType, title, StringPool.BLANK, null, null,
				fileContent, null, null, null, serviceContext);
		}
	}

	@Override
	public FileEntry getOrAddFileEntry(
			long companyId, String externalReferenceCode, byte[] fileContent,
			String fileName, String folderExternalReferenceCode, long groupId,
			long objectFieldId, ServiceContext serviceContext)
		throws Exception {

		FileEntry fileEntry =
			_dlAppLocalService.fetchFileEntryByExternalReferenceCode(
				groupId, externalReferenceCode);

		if ((fileEntry != null) && (companyId == fileEntry.getCompanyId())) {
			return fileEntry;
		}

		_validateObjectDefinitionSettings(
			fileContent, fileName, objectFieldId, serviceContext.getUserId());

		long repositoryId = groupId;
		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (Validator.isNotNull(folderExternalReferenceCode)) {
			DLFolder dlFolder =
				_dlFolderService.getDLFolderByExternalReferenceCode(
					folderExternalReferenceCode, groupId);

			if (dlFolder.getCompanyId() != companyId) {
				throw new NoSuchFolderException();
			}

			repositoryId = dlFolder.getRepositoryId();
			folderId = dlFolder.getFolderId();
		}

		ServiceContext cloneServiceContext =
			(ServiceContext)serviceContext.clone();

		cloneServiceContext.setCompanyId(companyId);

		try (InputStream inputStream = new ByteArrayInputStream(fileContent)) {
			String title = DLUtil.getUniqueTitle(
				groupId, folderId, FileUtil.stripExtension(fileName));
			String sourceFileName = DLUtil.getUniqueFileName(
				groupId, folderId, fileName, true);
			String mimeType = _mimeTypes.getContentType(inputStream, fileName);

			_validateDLSettings(
				companyId, groupId,
				DLAppUtil.getExtension(title, sourceFileName), mimeType,
				fileContent.length, sourceFileName);

			return _dlAppService.addFileEntry(
				externalReferenceCode, repositoryId, folderId, sourceFileName,
				mimeType, title, StringPool.BLANK, null, null, fileContent,
				null, null, null, cloneServiceContext);
		}
	}

	@Override
	public boolean isFileEntryDeletable(ObjectField objectField) {
		ObjectFieldSetting objectFieldSetting =
			_objectFieldSettingLocalService.fetchObjectFieldSetting(
				objectField.getObjectFieldId(),
				ObjectFieldSettingConstants.NAME_FILE_SOURCE);

		if (!Objects.equals(
				objectFieldSetting.getValue(),
				ObjectFieldSettingConstants.VALUE_USER_COMPUTER)) {

			return false;
		}

		objectFieldSetting =
			_objectFieldSettingLocalService.fetchObjectFieldSetting(
				objectField.getObjectFieldId(),
				ObjectFieldSettingConstants.NAME_SHOW_FILES_IN_DOCS_AND_MEDIA);

		if (objectFieldSetting == null) {
			return true;
		}

		return !GetterUtil.getBoolean(objectFieldSetting.getValue());
	}

	@Override
	public void validateFileExtension(String fileName, long objectFieldId)
		throws FileExtensionException {

		String[] acceptedFileExtensions = getAcceptedFileExtensions(
			objectFieldId);

		if (!ArrayUtil.contains(acceptedFileExtensions, StringPool.STAR) &&
			!ArrayUtil.contains(
				acceptedFileExtensions, FileUtil.getExtension(fileName),
				true)) {

			throw new FileExtensionException.InvalidExtension(
				"Invalid file extension for " + fileName);
		}
	}

	@Override
	public void validateFileName(String fileName) throws FileNameException {
		if (Validator.isNull(fileName)) {
			throw new FileNameException("File name is null");
		}
	}

	@Override
	public void validateFileSize(
			String fileName, long fileSize, long objectFieldId,
			boolean signedIn)
		throws FileSizeException {

		long maximumFileSize = getMaximumFileSize(objectFieldId, signedIn);

		if ((maximumFileSize > 0) && (fileSize > maximumFileSize)) {
			throw new FileSizeException(
				StringBundler.concat(
					"File ", fileName,
					" exceeds the maximum permitted size of ",
					maximumFileSize / _FILE_LENGTH_MB, " MB"));
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_objectConfiguration = ConfigurableUtil.createConfigurable(
			ObjectConfiguration.class, properties);
	}

	private long _getFileEntryId(Object value) {
		if (value instanceof Number) {
			return GetterUtil.getLong(value);
		}

		if (value instanceof Map) {
			Map<String, Object> valueMap = (Map<String, Object>)value;

			return GetterUtil.getLong(valueMap.get("id"));
		}

		return 0;
	}

	private long _getObjectFieldSettingMaximumFileSize(long objectFieldId) {
		ObjectFieldSetting objectFieldSetting =
			_objectFieldSettingLocalService.fetchObjectFieldSetting(
				objectFieldId, ObjectFieldSettingConstants.NAME_MAX_FILE_SIZE);

		long value = GetterUtil.getLong(objectFieldSetting.getValue());

		if (value == 0) {
			return Long.MAX_VALUE;
		}

		return value * _FILE_LENGTH_MB;
	}

	private Repository _getRepository(
			long groupId, String portletId, ServiceContext serviceContext)
		throws PortalException {

		Repository repository = _portletFileRepository.fetchPortletRepository(
			groupId, portletId);

		if (repository != null) {
			return repository;
		}

		serviceContext = (ServiceContext)serviceContext.clone();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return _portletFileRepository.addPortletRepository(
			groupId, portletId, serviceContext);
	}

	private Long _getRepositoryFolderId(
			long companyId, long groupId, String portletId,
			ServiceContext serviceContext, long userId)
		throws PortalException {

		Repository repository = _getRepository(
			groupId, portletId, serviceContext);

		if (repository == null) {
			return null;
		}

		DLFolder dlFolder = _dlFolderLocalService.fetchFolder(
			repository.getGroupId(), repository.getDlFolderId(),
			String.valueOf(userId));

		if (dlFolder != null) {
			return dlFolder.getFolderId();
		}

		dlFolder = _dlFolderLocalService.addFolder(
			null, _userLocalService.getGuestUserId(companyId),
			repository.getGroupId(), repository.getRepositoryId(), false,
			repository.getDlFolderId(), String.valueOf(userId), null, false,
			serviceContext);

		return dlFolder.getFolderId();
	}

	private Long _getStorageDLFolderId(
			long companyId, long groupId, ServiceContext serviceContext,
			String storageDLFolderPath)
		throws PortalException {

		long storageDLFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		for (String name :
				StringUtil.split(storageDLFolderPath, CharPool.FORWARD_SLASH)) {

			DLFolder dlFolder = _dlFolderLocalService.fetchFolder(
				groupId, storageDLFolderId, name);

			if (dlFolder != null) {
				storageDLFolderId = dlFolder.getFolderId();

				continue;
			}

			Folder folder = _dlAppLocalService.addFolder(
				null, _userLocalService.getGuestUserId(companyId), groupId,
				storageDLFolderId, name, null, serviceContext);

			storageDLFolderId = folder.getFolderId();
		}

		return storageDLFolderId;
	}

	private void _validateDLSettings(
			long companyId, long groupId, String fileExtension, String mimeType,
			long size, String sourceFileName)
		throws PortalException {

		_dlValidator.validateFileName(sourceFileName);

		_dlValidator.validateFileExtension(sourceFileName);

		if (size != 0) {
			_dlValidator.validateFileMimeType(companyId, mimeType);
		}

		try {
			_dlValidator.validateFileSize(
				groupId, sourceFileName, mimeType, size);
		}
		catch (FileSizeException fileSizeException) {
			throw new FileSizeException(
				StringBundler.concat(
					"File ", sourceFileName,
					" exceeds the maximum permitted size of ",
					fileSizeException.getMaxSize() / _FILE_LENGTH_MB, " MB"));
		}

		_dlValidator.validateSourceFileExtension(fileExtension, sourceFileName);
	}

	private void _validateObjectDefinitionSettings(
			byte[] fileContent, String fileName, long objectFieldId,
			long userId)
		throws Exception {

		validateFileName(fileName);
		validateFileExtension(fileName, objectFieldId);

		User user = _userLocalService.getUser(userId);

		validateFileSize(
			fileName, fileContent.length, objectFieldId, !user.isGuestUser());
	}

	private static final long _FILE_LENGTH_MB = 1024 * 1024;

	private static final Log _log = LogFactoryUtil.getLog(
		AttachmentManagerImpl.class);

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFolderLocalService _dlFolderLocalService;

	@Reference
	private DLFolderService _dlFolderService;

	@Reference
	private DLValidator _dlValidator;

	@Reference
	private MimeTypes _mimeTypes;

	private volatile ObjectConfiguration _objectConfiguration;

	@Reference
	private ObjectFieldSettingLocalService _objectFieldSettingLocalService;

	@Reference
	private PortletFileRepository _portletFileRepository;

	@Reference
	private UploadServletRequestConfigurationProvider
		_uploadServletRequestConfigurationProvider;

	@Reference
	private UserLocalService _userLocalService;

}