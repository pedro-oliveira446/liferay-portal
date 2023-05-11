<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String backURL = ParamUtil.getString(request, "backURL", String.valueOf(renderResponse.createRenderURL()));
String externalReferenceCode = ParamUtil.getString(request, "externalReferenceCode");

ObjectEntryDisplayContext objectEntryDisplayContext = (ObjectEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ObjectDefinition objectDefinition = objectEntryDisplayContext.getObjectDefinition1();
ObjectLayoutTab objectLayoutTab = objectEntryDisplayContext.getObjectLayoutTab();

List<String> activeLanguaglistaDeStringsIdsTest = new ArrayList<String>();
activeLanguaglistaDeStringsIdsTest.add("en_US");
activeLanguaglistaDeStringsIdsTest.add("pt_BR");
%>

<c:if test="<%= (objectEntryDisplayContext.getObjectEntry() != null) && (objectLayoutTab != null) %>">
	<liferay-frontend:screen-navigation
		key="<%= objectDefinition.getClassName() %>"
		portletURL="<%= currentURLObj %>"
	/>
</c:if>

<nav class="component-tbar subnav-tbar-light tbar tbar-metadata-type">
	<clay:container-fluid>
		<ul class="tbar-nav">
			<li class="tbar-item tbar-item-expand">
				<liferay-frontend:translation-manager
					availableLocales="<%= LocaleUtil.fromLanguageIds(activeLanguaglistaDeStringsIdsTest) %>"
					changeableDefaultLanguage="<%= false %>"
					defaultLanguageId="<%= LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()) %>"
					id="translationManager"
				/>
			</li>
			<li class="tbar-item">
				<h4 class="component-title text-left ml-8">
					<%= objectDefinition.getLabel(LocaleUtil.getSiteDefault(), true) %>
					<c:if test="<%= (objectEntryDisplayContext.getObjectEntry() != null) %>">
						<%= objectEntryDisplayContext.getObjectEntry().getObjectEntryId() %>
					</c:if>
				</h4>
			</li>
			<li class="tbar-item">
				<c:if test="<%= !objectEntryDisplayContext.isReadOnly() %>">
					<div class="metadata-type-button-row tbar-section text-left">
						<div class="journal-article-button-row tbar-section text-right">
							<aui:button cssClass="mr-3" href="<%= backURL %>" type="cancel" />

							<aui:button cssClass="mr-3" id="submitButton" onClick='<%= "event.preventDefault(); " + liferayPortletResponse.getNamespace() + "submitObjectEntry();" %>' type="submit" value="save" />
						</div>
					</div>
				</c:if>
			</li>
		</ul>
	</clay:container-fluid>
</nav>
<c:choose>
	<c:when test="<%= (objectLayoutTab != null) && (objectLayoutTab.getObjectRelationshipId() > 0) %>">
		<liferay-util:include page="/object_entries/object_entry/relationship.jsp" servletContext="<%= application %>">
			<liferay-util:param name="externalReferenceCode" value="<%= externalReferenceCode %>" />
			<liferay-util:param name="objectLayoutTabId" value="<%= String.valueOf(objectLayoutTab.getObjectLayoutTabId()) %>" />
		</liferay-util:include>
	</c:when>
	<c:when test="<%= objectEntryDisplayContext.isShowObjectEntryForm() %>">
		<liferay-util:include page="/object_entries/object_entry/form.jsp" servletContext="<%= application %>">
			<liferay-util:param name="externalReferenceCode" value="<%= externalReferenceCode %>" />
		</liferay-util:include>
	</c:when>
</c:choose>