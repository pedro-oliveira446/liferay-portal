<#list dataFactory.getSequence(dataFactory.maxObjectEntryPageCount) as objectEntryPageCount>
	<#include "custom_object_definitions.ftl">

	<#assign
		name = objectDefinitionModel.getName()

		csvLineData = virtualHostModel.hostname + "," + groupModel.friendlyURL + "," + dataFactory.getDefaultListTypeEntryKey()
	/>

	<#list dataFactory.getLayoutDataItemTypes() as layoutDataItemType>
		<#assign contentLayoutModels = dataFactory.newContentPageLayoutModels(groupId, name + "_" +layoutDataItemType)

			segmentsExperienceModels = dataFactory.newSegmentsExperienceModels(contentLayoutModels)

			fragmentEntryLinkModels = dataFactory.newObjectFieldsFragmentEntryLinkModels(layoutDataItemType, contentLayoutModels, objectFieldModels, segmentsExperienceModels)
		/>

		<#list fragmentEntryLinkModels as fragmentEntryLinkModel>
			${dataFactory.toInsertSQL(fragmentEntryLinkModel)}
		</#list>

		<#list segmentsExperienceModels as segmentsExperienceModel>
			${dataFactory.toInsertSQL(segmentsExperienceModel)}
		</#list>

		<#list contentLayoutModels as contentLayoutModel>
			<#assign layoutPageTemplateStructureModel = dataFactory.newLayoutPageTemplateStructureModel(contentLayoutModel) />

			${dataFactory.toInsertSQL(contentLayoutModel)}

			${dataFactory.toInsertSQL(dataFactory.newLayoutFriendlyURLModel(contentLayoutModel))}

			${dataFactory.toInsertSQL(layoutPageTemplateStructureModel)}

			<#assign layoutPageTemplateStructureRelModel = dataFactory.newObjectDefinitionLayoutPageTemplateStructureRelModel(fragmentEntryLinkModels, layoutDataItemType, contentLayoutModel, layoutPageTemplateStructureModel, objectDefinitionModel) />

			${dataFactory.toInsertSQL(layoutPageTemplateStructureRelModel)}

			<#if contentLayoutModel.friendlyURL?contains(name?c_lower_case)>
				<#assign csvLineData = csvLineData + "," + contentLayoutModel.getFriendlyURL() + "," + layoutPageTemplateStructureRelModel.getSegmentsExperienceId() />
			</#if>
		</#list>
	</#list>
	${csvFileWriter.write("objectDefinition", csvLineData + "\n")}
</#list>