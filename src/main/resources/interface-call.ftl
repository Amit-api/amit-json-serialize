<#import "macros.ftl" as my>
<#assign modelJavaPackage = my.getJavaPackage() >
<#assign thisJavaPackage = modelJavaPackage + ".call" >
<#assign runtimeJavaPackage ="com.amitapi.json.runtime" >
<#assign objectName = object.getName() >
/**
 * This file is generated by the Amit
 * don't modify it manually
 */
package ${thisJavaPackage};

/**
 * interface ${objectName} call data
 */
public final class Call${objectName} {

<#list object.getFunctions() as function >
<#assign fname = function.getName() >
<#assign ARfname = amit.AUpper( fname ) + "Request"  >
<#assign APfname = amit.AUpper( fname ) + "Response"  >
		
/**************************************************************************************************************
 * function ${ARfname}
 * 
 **************************************************************************************************************/
public static final class ${ARfname} extends ${runtimeJavaPackage}.__JsonSerializableType {
<@my.classMembers items=function.getArguments() />
<@my.classGettersSetters items=function.getArguments() className=ARfname />

<@my.hashCodeFunction items=function.getArguments() hasBaseType=false />

<@my.equalsFunction items=function.getArguments() className=ARfname hasBaseType=false />

<@my.toStringFunction items=function.getArguments() className=ARfname hasBaseType=false />

<@my.serializeFuctions className=ARfname hasBaseType=false />

<@my.serializeMembersFunction items=function.getArguments() hasBaseType=false />

<@my.parseTokenFunction items=function.getArguments() hasBaseType=false />

<@my.classFactory name=ARfname />	
}		

<#assign returnTypeName = function.getReturn().getTypeName() >
<#assign returnType = my.javaType( function.getReturn() ) >
<#assign returnTypeNoArray = my.javaTypeNoArray( function.getReturn() ) >
<#assign exceptions = function.getThrowsExceptionNames() >
<#assign allExceptions = project.getExceptionTypeChildren( exceptions ) >
<#assign returnTypeIsArray = function.getReturn().isArray() >
/**************************************************************************************************************
 * function ${APfname}
 * 
 **************************************************************************************************************/
public static final class ${APfname} extends ${runtimeJavaPackage}.__JsonSerializableType {
<#if returnTypeName != "void" >
	private ${returnType} returnValue;
</#if>	
	private ${runtimeJavaPackage}.__JsonSerializableException exception;	
<#if returnTypeName != "void" >
	
	/**
	 * sets return value
	 */
	public void setReturnValue( ${returnType} returnValue ) {
		this.returnValue = returnValue;
	}
	
	/**
	 * gets return value
	 */
	public ${returnType} getReturnValue() {
		return returnValue;
	}
</#if>	

	public void setException( ${runtimeJavaPackage}.AmitRuntimeException exception ) {
		this.exception = exception;
	}
	
<#list exceptions as exception >
	public void setException( ${modelJavaPackage}.${exception} exception ) {
		this.exception = exception;
	}
	
</#list>	
	/**
	 * gets the exception
	 */
	public ${runtimeJavaPackage}.__JsonSerializableException getException() {
		return exception;
	}

	@Override
	public void __serialize( com.fasterxml.jackson.core.JsonGenerator jg ) throws java.io.IOException {
		if( exception != null ) {
			exception.__serialize( jg );
			return;
		}
<#if returnTypeName != "void" >

		if( returnValue != null ) {		
			${runtimeJavaPackage}.__AmitJsonSerialize.
				writeJsonSerializable( jg, null, returnValue );
			
		}
</#if>
	}
	
	public static ${APfname} __deserialize( com.fasterxml.jackson.core.JsonParser jp ) throws java.io.IOException {
		${APfname} result = new ${APfname}();
		jp.nextToken();
		
<#if returnTypeName == "void" >
		if( jp.getCurrentToken() == null ) {
			return result;
		}
		
		result.exception = (${runtimeJavaPackage}.__JsonSerializableException) ${runtimeJavaPackage}.
			__AmitJsonSerialize.readJsonSerializable( jp,"<*>", __getFactoryMap(), null );
<#else>
		<#if returnTypeIsArray >
		if( jp.isExpectedStartArrayToken() ) {
			if( result.returnValue == null ) {
				result.returnValue = new java.util.ArrayList<${returnTypeNoArray}>();
			}
			${runtimeJavaPackage}.__AmitJsonSerialize.readJsonSerializable(
					jp, "<*>", ${returnTypeNoArray}.__getFactoryMap(), ${returnTypeNoArray}.__getFactory(), result.returnValue );			
		} else {
			result.exception = (${runtimeJavaPackage}.__JsonSerializableException) ${runtimeJavaPackage}.
					__AmitJsonSerialize.readJsonSerializable( jp,"<*>", __getFactoryMap(), null );			
		}
		<#else>
			${runtimeJavaPackage}.__JsonSerializable object = ${runtimeJavaPackage}.__AmitJsonSerialize.
				readJsonSerializable( jp, "<*>", __getFactoryMap(), ${returnTypeNoArray}.__getFactory() );
			if( object.__isExceptionType()  ) {
				result.exception = (${runtimeJavaPackage}.__JsonSerializableException)object;
			} else {
				result.returnValue = (${returnTypeNoArray})object;
			}
		</#if>
</#if>
		return result;
}	
	
	/**
	 * !!! only for internal use
	 */
	public static ${runtimeJavaPackage}.__JsonSerializableFactoryMap __getFactoryMap() {
		return OnDemandInit.FACTORYMAP;
	}
	private static class OnDemandInit {
		private static final ${runtimeJavaPackage}.__JsonSerializableFactoryMap FACTORYMAP = 
				new ${runtimeJavaPackage}.__JsonSerializableFactoryMap()
<#list allExceptions as ex >
			.with( ${modelJavaPackage}.${ex}.__getFactory() )
</#list>
			.with( ${runtimeJavaPackage}.AmitInternalErrorException.__getFactory() )
			.with( ${runtimeJavaPackage}.AmitInvalidRequestException.__getFactory() )
			.with( ${runtimeJavaPackage}.AmitInvalidResponseException.__getFactory() )
			.with( ${runtimeJavaPackage}.AmitRuntimeException.__getFactory() )
<#if returnTypeName != "void" >
	<#if !returnTypeIsArray >
			.with( ${returnTypeNoArray}.__getFactory() )
	</#if>
</#if>
		;
	}
}

</#list>
}