
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

<#function baseType object >
	<#if object.getBaseTypeName()?? >
		<#return "extends " + object.getBaseTypeName() >
	<#else>
		<#return ""> 
	</#if>
</#function>
 
<#function deserializeMember member >
		<#switch member.getTypeName()>
		<#case "boolean">
			<#return "j.getBooleanValue()" >
		<#break>
		<#case "int">
			<#return "j.getIntValue()" >
		<#break>
		<#case "string">
			<#return "j.getText()" >
		<#break>
		<#case "long">
			<#return "j.getLongValue()" >
		<#break>
		<#case "double">
			<#return "j.getDoubleValue()" >
		<#break>
		<#case "uuid">
			<#return "UUID.fromString( j.getText() )" >
		<#break>
		<#case "datetime">
			<#return "LocalDateTime.parse( j.getText() )" >
		<#break>
		<#default>
			<#return member.getTypeName() + ".deserialize( j )" >
		</#switch>
</#function>
 