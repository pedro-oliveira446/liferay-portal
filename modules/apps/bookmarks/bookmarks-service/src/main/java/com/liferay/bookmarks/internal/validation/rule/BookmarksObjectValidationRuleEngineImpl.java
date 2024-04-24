package com.liferay.bookmarks.internal.validation.rule;

import com.liferay.object.scope.ObjectDefinitionScoped;
import com.liferay.object.validation.rule.ObjectValidationRuleEngine;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.HashMapBuilder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(service = ObjectValidationRuleEngine.class)
public class BookmarksObjectValidationRuleEngineImpl implements
	ObjectDefinitionScoped, ObjectValidationRuleEngine {

	@Override
	public Map<String, Object> execute(
		Map<String, Object> inputObjects, String script) {

		Map<String, Object> results = HashMapBuilder.<String, Object>put(
			"validationCriteriaMet", true
		).build();

		return results;
	}

	@Override
	public List<String> getAllowedObjectDefinitionNames() {
		return Arrays.asList("Bookmark");
	}

	@Override
	public String getKey() {
		return "javaDelegate#Bookmark";
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, getKey());
	}

	@Reference
	private Language _language;
}