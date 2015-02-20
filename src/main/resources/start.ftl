Start generate ...

<#assign attrJavaPackage = project.getProjectModule().getAttributeValue( "java_package", "com.noname" ) >
<#assign resultPath = amit.toPath( attrJavaPackage, "\\.") >
<#list amit.generate( "type","type.ftl", resultPath + "/%s.java" ) as processed>
done: ${processed}
</#list>
End generate.
