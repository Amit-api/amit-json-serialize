<#import "macros.ftl" as my>
<#assign attrJavaPackage = project.getProjectModule().getAttributeValue( "java_package", "com.noname" ) >
<#assign objectName = object.getName() >
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
import com.amitapi.json.runtime.*;

/**
 * type ${objectName}
 */
public class ${objectName} ${my.baseType( object )} {
	private static final long serialVersionUID = 1L;

<@my.classMembers items=object.getMembers() />
<@my.classGettersSetters items=object.getMembers() className=objectName />

<@my.hashCodeFunction items=object.getMembers() hasBaseType=object.getBaseTypeName()?? />

<@my.equalsFunction items=object.getMembers() className=objectName hasBaseType=object.getBaseTypeName()?? />

<@my.toStringFunction items=object.getMembers() className=objectName hasBaseType=object.getBaseTypeName()?? />

<@my.serializeFuctions className=objectName />

<@my.serializeMembersFunction items=object.getMembers() hasBaseType=object.getBaseTypeName()?? />
		
<@my.parseTokenFunction items=object.getMembers() hasBaseType=object.getBaseTypeName()?? />
	
<@my.classFactory name=objectName children=project.getExceptionTypeChildren( objectName ) />
}