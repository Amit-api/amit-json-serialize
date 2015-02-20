
<#function javaTypeNoArray member >

	<#assign value = "unknown" >
	<#switch member.getTypeName()>
		<#case "boolean">
			<#assign value = "Boolean" >
		<#break>
		<#case "int">
			<#assign value = "Integer" >
		<#break>
		<#case "string">
			<#assign value = "String" >
		<#break>
		<#case "long">
			<#assign value = "Long" >
		<#break>
		<#case "double">
			<#assign value = "Double" >
		<#break>
		<#case "uuid">
			<#assign value = "UUID" >
		<#break>
		<#case "datetime">
			<#assign value = "LocalDateTime" >
		<#break>
		<#default>
		<#assign value = member.getTypeName() >
	</#switch>

	<#return value >
</#function>

<#-- function to get java type from the model type -->
<#function javaType member >
	<#assign value = javaTypeNoArray( member ) >
	<#if member.isArray() >
		<#return "List<" + value + ">" >
	<#else>
		<#return value >
	</#if>
</#function>
