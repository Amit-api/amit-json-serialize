import com.amitapi.json.runtime.AmitJsonSerialize;
import com.fasterxml.jackson.core.JsonParser;


<#-- *********************************************************************************************** -->
<#-- *********************************************************************************************** -->
<#function javaTypeNoArray member >

	<#assign value = "unknown" >
	<#switch member.getTypeName()>
		<#case "void">
			<#assign value = "void" >
		<#break>
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

<#-- *********************************************************************************************** -->
<#-- *********************************************************************************************** -->
<#function jsonSerializeType member >

	<#assign value = "unknown" >
	<#switch member.getTypeName()>
		<#case "void">
			<#assign value = "void" >
		<#break>
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
			<#if project.isEnumType( member.getTypeName() ) >
				<#assign value = "JsonSerializableEnum" >
			<#else>
				<#assign value = "JsonSerializable" >
			</#if>
	</#switch>

	<#return value >
</#function>

<#-- *********************************************************************************************** -->
<#-- function to get java type from the model type -->
<#-- *********************************************************************************************** -->
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
		<#if object.getType() == "exception" >
			<#return "extends JsonSerializableException">
		<#else>
			<#return "implements JsonSerializable">
		</#if>
	</#if>
</#function>

<#-- *********************************************************************************************** -->
<#-- generates a class factory -->
<#-- *********************************************************************************************** -->
<#macro classFactory name children=[] >
	/**
	 * ${name} factory
	 * !!! only for internal use
	 */
	public static final JsonSerializableFactory FACTORY = new JsonSerializableFactory() {
		public String getName() { return "${name}"; }
		public JsonSerializable create() { return new ${name}(); }
	};

<#if children?size !=0 >	
	/**
	 * ${name} factory map
	 * !!! only for internal use
	 */
	public static JsonSerializableFactoryMap getFactoryMap() {
		return OnDemandInit.FACTORYMAP;
	}
	private static class OnDemandInit {
		private static final JsonSerializableFactoryMap FACTORYMAP = new JsonSerializableFactoryMap()
<#list  children as childTypeName >
			.with( ${childTypeName}.FACTORY )
</#list>	
			.with( ${name}.FACTORY );
	}
<#else>
	/**
	 * ${name} factory map
	 * !!! only for internal use
	 */
	public static JsonSerializableFactoryMap getFactoryMap() {
		return null;
	}
</#if>
</#macro>


<#-- *********************************************************************************************** -->
<#-- generates a parseToken function                                                                 -->
<#-- *********************************************************************************************** -->
<#macro parseTokenFunction items hasBaseType >
	/**
	 * {@inheritDoc}
	 * !!! only for internal use
	 */	
	@Override
	public boolean parseToken( JsonParser jp, String fieldName ) throws IOException {
<#if hasBaseType >
		if( super.parseToken( jp, fieldName ) ) {
			return true;
		}
</#if>
<#list items as item >
	<#assign stype = jsonSerializeType( item ) >
	<#assign name = item.getName() >
	<#assign type = item.getTypeName() >
	<#assign noarrayjtype = javaTypeNoArray( item ) >
	<#if item.isArray() >
		if( fieldName.equals( "${name}s" ) ) {
		<#if project.isPrimitiveType( type ) >
			${name} = AmitJsonSerialize.read${stype}( jp, fieldName, ${name} );
		<#elseif project.isEnumType( type ) >
			${name} = AmitJsonSerialize.read${stype}( jp, fieldName, ${name}, ${noarrayjtype}.values() );
		<#else>
			if( ${name} == null ) {
				${name} = new ArrayList<${noarrayjtype}>();
			}
			AmitJsonSerialize.readJsonSerializable( 
					jp, fieldName, ${noarrayjtype}.getFactoryMap(), ${noarrayjtype}.FACTORY, ${name} );
		</#if>
			return true;
		}
	<#else>
		if( fieldName.equals( "${name}" ) ) {
		<#if project.isPrimitiveType( type ) >
			${name} = AmitJsonSerialize.read${stype}( jp, fieldName );
		<#elseif project.isEnumType( type ) >
			${name} = AmitJsonSerialize.read${stype}( jp, fieldName, ${type}.values() );
		<#else>
			${name} = (${type})AmitJsonSerialize.
				readJsonSerializable( jp, fieldName, ${type}.getFactoryMap(), ${type}.FACTORY );
		</#if>
			return true;
		} <#if item_has_next> else</#if> 
	</#if>
</#list>
		return false;
	}
</#macro>
<#-- *********************************************************************************************** -->
<#-- generates class members                                                                         -->
<#-- *********************************************************************************************** -->
<#macro classMembers items >
<#list items as item >
	<#assign jtype = javaType( item ) >
	<#assign name = item.getName() >	
	private ${jtype} ${name};
</#list>
</#macro>

<#-- *********************************************************************************************** -->
<#-- generates class getters and setters                                                             -->
<#-- *********************************************************************************************** -->
<#macro classGettersSetters items className >
<#list items as item >
	<#assign jtype = javaType( item ) >
	<#assign noarrayjtype = javaTypeNoArray( item ) >
	<#assign name = item.getName() >	
	<#assign Aname = amit.AUpper( item.getName() ) >
	
	<#if item.isArray() >
	public ${jtype} get${Aname}List() {
		return ${name};
	}
	public void set${Aname}List( ${jtype} ${name} ) {
		this.${name} = ${name};
	}
	public ${className} with${Aname}( ${noarrayjtype} ${name} ) {
		if( this.${name} == null ) {
			this.${name} = new ArrayList<${noarrayjtype}>();
		}
		this.${name}.add( ${name} );
		return this;	
	}
	<#else>
	public ${jtype} get${Aname}() {
		return ${name};
	}
	public void set${Aname}( ${jtype} ${name} ) {
		this.${name} = ${name};
	}
	public ${className} with${Aname}( ${jtype} ${name} ) {
		this.${name} = ${name};
		return this;	
	}		
	</#if>
</#list>
</#macro>

<#-- *********************************************************************************************** -->
<#-- generates hashCode function                                                                     -->
<#-- *********************************************************************************************** -->
<#macro hashCodeFunction items hasBaseType >
	@Override
	public int hashCode() {
<#if items?size == 0 >
		return 0;
<#else>
		return Objects.hash(
	<#if hasBaseType >
			super.hashCode()<#if items?size != 0 >,</#if>
	</#if>	
<#list items as item >
	<#assign name = item.getName() >	
			${name}<#if item_has_next>,</#if>
</#list>	
		);
</#if>		
	}
</#macro>

<#-- *********************************************************************************************** -->
<#-- generates equals function                                                                       -->
<#-- *********************************************************************************************** -->
<#macro equalsFunction items hasBaseType className >
	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) return false;
		if( !( obj instanceof ${className} ) ) return false;
<#if hasBaseType >
		if( !super.equals( obj ) ) return false;
</#if>
<#if items?size == 0 >
		return true;
<#else>		
		${className} other = (${className}) obj;
		return 
<#list items as item >
	<#assign name = item.getName() >	
			Objects.equals( this.${name}, other.${name} ) <#if item_has_next>&&</#if>
</#list>	
		;
</#if>		
	}
</#macro>

<#-- *********************************************************************************************** -->
<#-- generates toString function                                                                     -->
<#-- *********************************************************************************************** -->
<#macro toStringFunction items hasBaseType className >
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append( "${className} [" );
		toString( sb );
		sb.append( "]" );
		return sb.toString();
	}
	
<#if hasBaseType >
	@Override	
</#if>	
	protected void toString( StringBuffer sb ) { 
<#if hasBaseType >
		super.toString( sb );
</#if>	
<#list items as item >
	<#assign name = item.getName() >	
		sb.append( "${name}" ); sb.append( "=" ); sb.append( ${name} );<#if item_has_next>sb.append( "," );</#if>
</#list>
	}
</#macro>

<#-- *********************************************************************************************** -->
<#-- generates serialize functions                                                                   -->
<#-- *********************************************************************************************** -->
<#macro serializeFuctions className hasBaseType=true >
	/**
	 * {@inheritDoc} serialize ${className} type
	 */	
<#if hasBaseType >
	@Override
</#if>	
	public void serialize( JsonGenerator jg ) throws IOException {
		jg.writeStartObject();
		jg.writeFieldName( AmitJsonSerialize.typeFieldName );
		jg.writeString( "${className}" );
		serializeMembers( jg );
		jg.writeEndObject();
	}

	/**
	 * deserialize an object of instance of ${className}
	 */
	public static ${className} deserialize( JsonParser jp ) throws IOException {
		jp.nextToken();
		return (${className})AmitJsonSerialize.
				readJsonSerializable( jp, "<root>",${className}.getFactoryMap(), ${className}.FACTORY );
	}	
</#macro>

<#-- *********************************************************************************************** -->
<#-- generates serializeMembers functions                                                            -->
<#-- *********************************************************************************************** -->
<#macro serializeMembersFunction items hasBaseType >
	/**
	 * {@inheritDoc}
	 */	
	@Override
	public void serializeMembers( JsonGenerator jg ) throws IOException {
<#if hasBaseType >
		super.serializeMembers( jg );
</#if>	
<#list items as item >
	<#assign stype = jsonSerializeType( item ) >
	<#assign name = item.getName() >	
	<#if item.isArray() >
		AmitJsonSerialize.write${stype}( jg, "${name}s", ${name} );
	<#else>
		AmitJsonSerialize.write${stype}( jg, "${name}", ${name} );
	</#if>
</#list>
	}
</#macro>
<#-- *********************************************************************************************** -->
<#-- generates throws exceptions                                                           -->
<#-- *********************************************************************************************** -->
<#macro throwsExceptions items >
<#if items?size != 0 >throws <#list items as item >${item}<#if item_has_next>, </#if></#list></#if></#macro>

<#-- *********************************************************************************************** -->
<#-- generates implements interfaces exceptions                                                           -->
<#-- *********************************************************************************************** -->
<#macro extendsInterfaces items >
<#if items?size != 0 >extends <#list items as item >${item}<#if item_has_next>, </#if></#list> </#if></#macro>
