<#-- *********************************************************************************************** -->
<#-- function to get java package -->
<#-- *********************************************************************************************** -->
<#function getJavaPackage >
	<#return project.getProjectModule().getAttributeValue( "java_package", "com.noname" ) >
</#function>
<#-- *********************************************************************************************** -->
<#-- *********************************************************************************************** -->
<#function javaTypeNoArray member >
	<#assign javaPackage = getJavaPackage() >
	<#assign value = "unknown" >
	<#switch member.getTypeName()>
		<#case "void">
			<#assign value = "void" >
		<#break>
		<#case "boolean">
			<#assign value = "java.lang.Boolean" >
		<#break>
		<#case "int">
			<#assign value = "java.lang.Integer" >
		<#break>
		<#case "string">
			<#assign value = "java.lang.String" >
		<#break>
		<#case "long">
			<#assign value = "java.lang.Long" >
		<#break>
		<#case "double">
			<#assign value = "java.lang.Double" >
		<#break>
		<#case "uuid">
			<#assign value = "java.util.UUID" >
		<#break>
		<#case "datetime">
			<#assign value = "java.time.LocalDateTime" >
		<#break>
		<#default>
			<#assign value = javaPackage + "." + member.getTypeName() >
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
		<#return "java.util.List<" + value + ">" >
	<#else>
		<#return value >
	</#if>
</#function>

<#-- *********************************************************************************************** -->
<#-- function to get b -->
<#-- *********************************************************************************************** -->
<#function baseType object >
	<#assign javaPackage = getJavaPackage() >
	<#if object.getBaseTypeName()?? >
		<#return "extends " + javaPackage + "." + object.getBaseTypeName() >
	<#else>
		<#if object.getType() == "exception" >
			<#return "extends com.amitapi.json.runtime.__JsonSerializableException">
		<#else>
			<#return "extends com.amitapi.json.runtime.__JsonSerializableType">
		</#if>
	</#if>
</#function>

<#-- *********************************************************************************************** -->
<#-- generates class members                                                                         -->
<#-- *********************************************************************************************** -->
<#macro classMembers items >
<#list items as item >
	<#assign jtype = javaType( item ) >
	<#assign name = item.getName() >	
	private ${jtype} __${name};
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
		return this.__${name};
	}
	public void set${Aname}List( ${jtype} value ) {
		this.__${name} = value;
	}
	public ${className} with${Aname}( ${noarrayjtype} value ) {
		if( this.__${name} == null ) {
			this.__${name} = new java.util.ArrayList<${noarrayjtype}>();
		}
		this.__${name}.add( value );
		return this;	
	}
	<#else>
	public ${jtype} get${Aname}() {
		return __${name};
	}
	public void set${Aname}( ${jtype} value ) {
		this.__${name} = value;
	}
	public ${className} with${Aname}( ${jtype} value ) {
		this.__${name} = value;
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
		return java.util.Objects.hash(
	<#if hasBaseType >
			super.hashCode()<#if items?size != 0 >,</#if>
	</#if>	
<#list items as item >
	<#assign name = item.getName() >	
			this.__${name}<#if item_has_next>,</#if>
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
	public boolean equals( java.lang.Object obj ) {
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
			java.util.Objects.equals( this.__${name}, other.__${name} ) <#if item_has_next>&&</#if>
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
		java.lang.StringBuffer sb = new java.lang.StringBuffer();
		sb.append( "${className} [" );
		toString( sb );
		sb.append( "]" );
		return sb.toString();
	}
	
<#if hasBaseType >
	@Override	
</#if>	
	protected void toString( java.lang.StringBuffer sb ) { 
<#if hasBaseType >
		super.toString( sb );
</#if>	
<#list items as item >
	<#assign name = item.getName() >	
		sb.append( "${name}" ); sb.append( "=" ); sb.append( this.__${name} );<#if item_has_next>sb.append( "," );</#if>
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
	public void __serialize( com.fasterxml.jackson.core.JsonGenerator jg ) throws java.io.IOException {
		jg.writeStartObject();
		jg.writeFieldName( com.amitapi.json.runtime.__AmitJsonSerialize.typeFieldName );
		jg.writeString( "${className}" );
		__serializeMembers( jg );
		jg.writeEndObject();
	}

	/**
	 * deserialize an object of instance of ${className}
	 */
	public static ${className} __deserialize( com.fasterxml.jackson.core.JsonParser jp ) throws java.io.IOException {
		jp.nextToken();
		return (${className})com.amitapi.json.runtime.__AmitJsonSerialize.
				readJsonSerializable( jp, "<root>",${className}.__getFactoryMap(), ${className}.__getFactory() );
	}	
</#macro>

<#-- *********************************************************************************************** -->
<#-- generates a class factory -->
<#-- *********************************************************************************************** -->
<#macro classFactory name children=[] >
<#assign javaPackage = getJavaPackage() >
	/**
	 * !!! only for internal use
	 */
	public static com.amitapi.json.runtime.__JsonSerializableFactory __getFactory() {
		return OnDemandInit1.FACTORY;
	}
	private static class OnDemandInit1 {
		public static final com.amitapi.json.runtime.__JsonSerializableFactory FACTORY = 
				new com.amitapi.json.runtime.__JsonSerializableFactory() {
					public java.lang.String getName() { return "${name}"; }
					public com.amitapi.json.runtime.__JsonSerializable create() { return new ${name}(); }
			};
	}

<#if children?size !=0 >	
	/**
	 * ${name} factory map
	 * !!! only for internal use
	 */
	public static com.amitapi.json.runtime.__JsonSerializableFactoryMap __getFactoryMap() {
		return OnDemandInit2.FACTORYMAP;
	}
	private static class OnDemandInit2 {
		private static final com.amitapi.json.runtime.__JsonSerializableFactoryMap FACTORYMAP = 
			new com.amitapi.json.runtime.__JsonSerializableFactoryMap()
<#list  children as childTypeName >
				.with( ${javaPackage}.${childTypeName}.__getFactory() )
</#list>	
				.with( ${name}.__getFactory() );
	}
<#else>
	/**
	 * ${name} factory map
	 * !!! only for internal use
	 */
	public static com.amitapi.json.runtime.__JsonSerializableFactoryMap __getFactoryMap() {
		return null;
	}
</#if>
</#macro>


<#-- *********************************************************************************************** -->
<#-- generates a parseToken function                                                                 -->
<#-- *********************************************************************************************** -->
<#macro parseTokenFunction items hasBaseType >
<#assign javaPackage = getJavaPackage() >
	/**
	 * {@inheritDoc}
	 * !!! only for internal use
	 */	
	@Override
	public boolean __parseToken( com.fasterxml.jackson.core.JsonParser jp, java.lang.String fieldName ) throws java.io.IOException {
<#if hasBaseType >
		if( super.__parseToken( jp, fieldName ) ) {
			return true;
		}
</#if>
<#list items as item >
	<#assign stype = jsonSerializeType( item ) >
	<#assign jtype = javaType( item ) >
	<#assign name = item.getName() >
	<#assign type = item.getTypeName() >
	<#assign noarrayjtype = javaTypeNoArray( item ) >
	<#if item.isArray() >
		if( fieldName.equals( "${name}s" ) ) {
		<#if project.isPrimitiveType( type ) >
			this.__${name} = com.amitapi.json.runtime.__AmitJsonSerialize.
				read${stype}( jp, fieldName, this.__${name} );
		<#elseif project.isEnumType( type ) >
			this.__${name} = com.amitapi.json.runtime.__AmitJsonSerialize.
				read${stype}( jp, fieldName, this.__${name}, ${noarrayjtype}.values() );
		<#else>
			if( this.__${name} == null ) {
				this.__${name} = new java.util.ArrayList<${noarrayjtype}>();
			}
			com.amitapi.json.runtime.__AmitJsonSerialize.readJsonSerializable( 
					jp, fieldName,
					${noarrayjtype}.__getFactoryMap(),
					${noarrayjtype}.__getFactory(),
					this.__${name} );
		</#if>
			return true;
		}
	<#else>
		if( fieldName.equals( "${name}" ) ) {
		<#if project.isPrimitiveType( type ) >
			this.__${name} = com.amitapi.json.runtime.__AmitJsonSerialize.
				read${stype}( jp, fieldName );
		<#elseif project.isEnumType( type ) >
			this.__${name} = com.amitapi.json.runtime.__AmitJsonSerialize.
				read${stype}( jp, fieldName, ${jtype}.values() );
		<#else>
			this.__${name} = (${jtype})com.amitapi.json.runtime.__AmitJsonSerialize.
				readJsonSerializable( jp, fieldName,
						${jtype}.__getFactoryMap(),
						${jtype}.__getFactory() );
		</#if>
			return true;
		} <#if item_has_next> else</#if> 
	</#if>
</#list>
		return false;
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
	public void __serializeMembers( com.fasterxml.jackson.core.JsonGenerator jg ) throws java.io.IOException {
<#if hasBaseType >
		super.__serializeMembers( jg );
</#if>	
<#list items as item >
	<#assign stype = jsonSerializeType( item ) >
	<#assign name = item.getName() >	
	<#if item.isArray() >
		com.amitapi.json.runtime.__AmitJsonSerialize.
			write${stype}( jg, "${name}s", this.__${name} );
	<#else>
		com.amitapi.json.runtime.__AmitJsonSerialize.
			write${stype}( jg, "${name}", this.__${name} );
	</#if>
</#list>
	}
</#macro>
<#-- *********************************************************************************************** -->
<#-- generates throws exceptions                                                           -->
<#-- *********************************************************************************************** -->
<#macro throwsExceptions items >
<#assign javaPackage = getJavaPackage() >
<#if items?size != 0 >throws
<#list items as item >
		${javaPackage}.${item}<#if item_has_next>, <#else>;</#if>
</#list>
<#else>
;
</#if>
</#macro>

<#-- *********************************************************************************************** -->
<#-- generates implements interfaces exceptions                                                           -->
<#-- *********************************************************************************************** -->
<#macro extendsInterfaces items >
<#assign javaPackage = getJavaPackage() >
<#if items?size != 0 >extends
<#list items as item >
		${javaPackage}.${item}<#if item_has_next>, </#if>
</#list>
</#if>						
</#macro>
