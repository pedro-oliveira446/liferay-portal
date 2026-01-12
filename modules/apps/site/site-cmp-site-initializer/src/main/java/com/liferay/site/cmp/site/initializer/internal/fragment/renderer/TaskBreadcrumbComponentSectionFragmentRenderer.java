/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.cmp.site.initializer.internal.fragment.renderer;

import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.layout.display.page.constants.LayoutDisplayPageWebKeys;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.cmp.site.initializer.internal.util.ActionUtil;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carolina Barbosa
 */
@Component(service = FragmentRenderer.class)
public class TaskBreadcrumbComponentSectionFragmentRenderer
	extends BaseComponentSectionFragmentRenderer {

	@Override
	public String getCollectionKey() {
		return "task-breadcrumb";
	}

	@Override
	protected String getComponentName() {
		return "Breadcrumb";
	}

	@Override
	protected String getLabelKey() {
		return "task-breadcrumb";
	}

	@Override
	protected String getModuleName() {
		return "site-cms-site-initializer";
	}

	@Override
	protected Map<String, Object> getProps(
			FragmentRendererContext fragmentRendererContext,
			HttpServletRequest httpServletRequest)
		throws Exception {

		LayoutDisplayPageObjectProvider<?> layoutDisplayPageObjectProvider =
			(LayoutDisplayPageObjectProvider<?>)httpServletRequest.getAttribute(
				LayoutDisplayPageWebKeys.LAYOUT_DISPLAY_PAGE_OBJECT_PROVIDER);

		if (layoutDisplayPageObjectProvider == null) {
			return null;
		}

		Object displayObject =
			layoutDisplayPageObjectProvider.getDisplayObject();

		if (!(displayObject instanceof ObjectEntry)) {
			return null;
		}

		ObjectEntry objectEntry = (ObjectEntry)displayObject;

		ObjectDefinition taskObjectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				objectEntry.getObjectDefinitionId());

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		ObjectDefinition projectObjectDefinition =
			_objectDefinitionLocalService.getObjectDefinition(
				themeDisplay.getCompanyId(), "CMPProject");

		List<ObjectEntry> objectEntries =
			_objectEntryLocalService.getObjectEntries(
				objectEntry.getGroupId(),
				projectObjectDefinition.getObjectDefinitionId(), 0, 1);

		if (ListUtil.isEmpty(objectEntries)) {
			return null;
		}

		String taskTitle = MapUtil.getString(objectEntry.getValues(), "title");

		String viewProjectURL = ActionUtil.getBaseViewProjectURL(
			projectObjectDefinition,
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY));

		return HashMapBuilder.<String, Object>put(
			"actionItems",
			_putAll(
				unsafeConsumer -> {
					if (_objectEntryService.hasModelResourcePermission(
							objectEntry, ActionKeys.UPDATE)) {

						unsafeConsumer.accept(
							JSONUtil.put(
								"href",
								StringBundler.concat(
									ActionUtil.getBaseEditTaskURL(
										taskObjectDefinition, themeDisplay),
									objectEntry.getObjectEntryId(),
									"?redirect=", themeDisplay.getURLCurrent())
							).put(
								"label",
								LanguageUtil.get(httpServletRequest, "edit")
							).put(
								"symbolLeft", "pencil"
							));
					}

					if (_objectEntryService.hasModelResourcePermission(
							objectEntry, ActionKeys.DELETE)) {

						unsafeConsumer.accept(
							JSONUtil.put(
								"confirmationMessage",
								LanguageUtil.format(
									httpServletRequest,
									"delete-asset-confirmation-body", taskTitle)
							).put(
								"confirmationTitle",
								LanguageUtil.format(
									httpServletRequest,
									"delete-asset-confirmation-title",
									taskTitle)
							).put(
								"href",
								StringBundler.concat(
									"/o",
									taskObjectDefinition.getRESTContextPath(),
									StringPool.SLASH,
									objectEntry.getObjectEntryId())
							).put(
								"label",
								LanguageUtil.get(httpServletRequest, "delete")
							).put(
								"redirect",
								ActionUtil.getProjectsURL(themeDisplay)
							).put(
								"successMessage",
								LanguageUtil.format(
									httpServletRequest,
									"x-was-successfully-deleted",
									StringBundler.concat(
										"<strong>", taskTitle, "</strong>"))
							).put(
								"symbolLeft", "trash"
							).put(
								"target", "asyncDelete"
							));
					}
				})
		).put(
			"breadcrumbItems",
			JSONUtil.putAll(
				JSONUtil.put(
					"active", false
				).put(
					"href", ActionUtil.getProjectsURL(themeDisplay)
				).put(
					"label", LanguageUtil.get(httpServletRequest, "projects")
				),
				JSONUtil.put(
					"active", false
				).put(
					"href",
					() -> {
						ObjectEntry projectObjectEntry = objectEntries.get(0);

						return viewProjectURL + projectObjectEntry.getObjectEntryId();
					}
				).put(
					"label",
					() -> {
						ObjectEntry projectObjectEntry = objectEntries.get(0);

						return MapUtil.getString(
							projectObjectEntry.getValues(), "title");
					}
				),
				JSONUtil.put(
					"active", true
				).put(
					"href", StringPool.BLANK
				).put(
					"label", taskTitle
				))
		).put(
			"hideSpace", true
		).put(
			"size", "lg"
		).build();
	}

	private JSONArray _putAll(
			UnsafeConsumer<UnsafeConsumer<JSONObject, Exception>, Exception>
				unsafeConsumer)
		throws Exception {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		unsafeConsumer.accept(jsonArray::put);

		if (jsonArray.length() == 0) {
			return null;
		}

		return jsonArray;
	}

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectEntryService _objectEntryService;

}