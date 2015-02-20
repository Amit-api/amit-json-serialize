<#import "macros.ftl" as my>
<#assign attrJavaPackage = project.getProjectModule().getAttributeValue( "java_package", "com.noname" ) >
/**
 * This file is generated by the Amit
 * don't modify it manually
 */
package ${attrJavaPackage};

import java.util.Objects;
<#if object.dependsOnTypeArray() >
import java.util.List;
import java.util.ArrayList;
</#if>
<#if object.dependsOnType( "datetime" ) >
import java.time.LocalDateTime;
</#if>
<#if object.dependsOnType( "uuid" ) >
import java.util.UUID;
</#if>

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * type ${object.getName()}
 */
public class ${object.getName()} ${my.baseType( object )} {
<#list object.getMembers() as member >
	private ${my.javaType( member ) } ${member.getName()};
</#list>	

<#list object.getMembers() as member >
	<#assign type = my.javaType( member ) >
	<#assign noarraytype = my.javaTypeNoArray( member ) >
	<#assign name = member.getName() >	
	<#assign Aname = amit.AUpper( member.getName() ) >	
	<#if member.isArray() >
	public ${type} get${Aname}List() {
		return ${name};
	}
	public void set${Aname}List( ${type} ${name} ) {
		this.${name} = ${name};
	}
	public ${object.getName()} with${Aname}( ${noarraytype} ${name} ) {
		if( this.${name} == null ) {
			this.${name} = new ArrayList<${noarraytype}>();
		}
		this.${name}.add( ${name} );
		return this;	
	}
	<#else>
	public ${type} get${Aname}() {
		return ${name};
	}
	public void set${Aname}( ${type} ${name} ) {
		this.${name} = ${name};
	}
	public ${object.getName()} with${Aname}( ${type} ${name} ) {
		this.${name} = ${name};
		return this;	
	}		
	</#if>

</#list>
	@Override
	public int hashCode() {
		return Objects.hash(
<#if object.getBaseTypeName()?? >
			super.hashCode()<#if object.getMembers()?size != 0 >,</#if>
</#if>	
<#list object.getMembers() as member >
			${member.getName()}<#if member_has_next>,</#if>
</#list>	
		);
	}

	@Override
	public boolean equals( Object obj ) {
		if( obj == null ) {
			return false;
		}

		if( !( obj instanceof ${object.getName()} ) ) {
			return false;
		} 

<#if object.getBaseTypeName()?? >
		if( !super.equals( obj ) ) {
			return false;
		}
		
</#if>
		${object.getName()} other = (${object.getName()}) obj;

		return 
<#list object.getMembers() as member >
			Objects.equals( this.${member.getName()}, other.${member.getName()} ) <#if member_has_next>&&</#if>
</#list>	
			;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append( "${object.getName()} [" );
		toString( sb );
		sb.append( "]" );
		return sb.toString();
	}

	protected void toString( StringBuffer sb ) { 
<#if object.getBaseTypeName()?? >
		super.toString( sb );
</#if>	
<#list object.getMembers() as member >		
		sb.append( "${member.getName()}" ); sb.append( "=" ); sb.append( ${member.getName()} );<#if member_has_next>sb.append( "," );</#if>
</#list>
	}
	
	/**
	 * streaming serialization to json
	 */	
	public void serialize( JsonGenerator j ) throws IOException {
		j.writeStartObject();
		j.writeFieldName( "__type" );
		j.writeString( "${object.getName()}" );
		serializeMembers( j );
		j.writeEndObject();
	}
	
	protected void serializeMembers( JsonGenerator j ) throws IOException {
<#if object.getBaseTypeName()?? >
		super.serializeMembers( j );
</#if>	
<#list object.getMembers() as member >
		if( ${member.getName()} != null ) {
	<#if member.isArray() >
			j.writeFieldName( "${member.getName()}s" );
			j.writeStartArray();
			for( ${my.javaTypeNoArray( member )} obj : ${member.getName()} ) {
		<#switch member.getTypeName()>
		<#case "boolean">
				j.writeBoolean( obj );
		<#break>
		<#case "int">
				j.writeNumber( obj );
		<#break>
		<#case "string">
				j.writeString( obj );
		<#break>
		<#case "long">
				j.writeNumber( obj );
		<#break>
		<#case "double">
				j.writeNumber( obj );
		<#break>
		<#case "uuid">
				j.writeString( obj.toString() );
		<#break>
		<#case "datetime">
				j.writeString( obj.toString() );
		<#break>
		<#default>
				obj.serialize( j );
		</#switch>
			}
			j.writeEndArray();
	<#else>
			j.writeFieldName( "${member.getName()}" );
		<#switch member.getTypeName()>
		<#case "boolean">
			j.writeBoolean( ${member.getName()} );
		<#break>
		<#case "int">
			j.writeNumber( ${member.getName()} );
		<#break>
		<#case "string">
			j.writeString( ${member.getName()} );
		<#break>
		<#case "long">
			j.writeNumber( ${member.getName()} );
		<#break>
		<#case "double">
			j.writeNumber( ${member.getName()} );
		<#break>
		<#case "uuid">
			j.writeString( ${member.getName()}.toString() );
		<#break>
		<#case "datetime">
			j.writeString( ${member.getName()}.toString() );
		<#break>
		<#default>
			${member.getName()}.serialize( j );
		</#switch>
	</#if>
		}
</#list>
	}
	
	public static ${object.getName()} deserialize( JsonParser j ) throws IOException {
		if( j.nextToken() != JsonToken.START_OBJECT ) {
			throw new IllegalArgumentException ( "Expected data to start with an Object" );
		}
		${object.getName()} result = new ${object.getName()}();
		
		while( j.nextToken() != JsonToken.END_OBJECT ) {
			String fieldName = j.getCurrentName();				
			j.nextToken();
			result.parseToken( fieldName, j );
		}
		return result;
	}
	
	protected boolean parseToken( String fieldName, JsonParser j ) throws IOException {
<#list object.getMembers() as member >
	<#assign noarraytype = my.javaTypeNoArray( member ) >
	<#assign name = member.getName() >	
	
	<#if member.isArray() >
		if( fieldName.equals( "${name}s" ) ) {
			if( j.isExpectedStartArrayToken() ) {
				if( ${name} == null ) {
					${name} = new ArrayList<${noarraytype}>();
				}
				while( j.nextToken() != JsonToken.END_ARRAY ) {
					${name}.add( ${ my.deserializeMember( member ) } );
				}
			}
		}
	<#else>
		if( fieldName.equals( "${name}" ) ) {
			${name} = ${ my.deserializeMember( member ) };
			return true;
		} <#if member_has_next> else</#if> 
	</#if>
</#list>
		return false;
	}
}
