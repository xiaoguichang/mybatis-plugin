package ${basePackage}.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
<#if useDate>
import java.util.Date;
</#if>

@Table(name = "${table}")
public class ${entityClassName} implements Serializable {
<#list attributes as attribute>

    /** ${attribute.comment} */
<#if attribute.id>
    @Id
    <#if attribute.autoIncrement>
    @GeneratedValue(generator = "JDBC")
    </#if>
</#if>
    @Column(name = "${attribute.column}")
    private ${attribute.type} ${attribute.name};
</#list>
<#list attributes as attribute>

    public ${attribute.type} get${attribute.methodNameSuffix}(){
        return ${attribute.name};
    }

    public void set${attribute.methodNameSuffix}(${attribute.type} ${attribute.name}) {
        this.${attribute.name} = ${attribute.name};
    }
</#list>

    public String toString() {
        final StringBuffer sb = new StringBuffer("${entityClassName}{");
    <#list attributes as attribute>
        <#if attribute?index == 0>
        sb.append("${attribute.name}=<#if attribute.type== "String">'</#if>").append(${attribute.name})<#if attribute.type== "String">.append("'")</#if>;
        <#else>
        sb.append(",${attribute.name}=<#if attribute.type== "String">'</#if>").append(${attribute.name})<#if attribute.type== "String">.append("'")</#if>;
        </#if>
    </#list>
        sb.append('}');
        return sb.toString();
    }
}

