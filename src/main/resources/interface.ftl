<#import "macros.ftl" as my>
<#assign attrJavaPackage = project.getProjectModule().getAttributeValue( "java_package", "com.noname" ) >
<#assign objectName = object.getName() >
/**
 * This file is generated by the Amit
 * don't modify it manually
 */
package ${attrJavaPackage};

<#if object.dependsOnTypeArray() >
import java.util.List;
</#if>
<#if object.dependsOnType( "datetime" ) >
import java.time.LocalDateTime;
</#if>
<#if object.dependsOnType( "uuid" ) >
import java.util.UUID;
</#if>

/**
 * interface ${objectName}
 */
public interface ${objectName} <@my.extendsInterfaces items=object.getBaseInterfaceNames() />{
<#list object.getFunctions() as function >
	<#assign fname = function.getName() >
	<#assign rtype = my.javaType( function.getReturn() ) >
	
	/**
	 * function ${fname}
	 */
	${rtype} ${fname}(
	<#list function.getArguments() as arg >
		<#assign aname = arg.getName() >
		<#assign atype = my.javaType( arg ) >
		${atype} ${aname}<#if arg_has_next>,</#if>
	</#list>
	) <@my.throwsExceptions items=function.getThrowsExceptionNames() />;
</#list>
}