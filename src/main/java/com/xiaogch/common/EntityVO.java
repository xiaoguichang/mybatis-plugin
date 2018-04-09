package com.xiaogch.common;

import java.util.List;

/**
 * ProjectName: mybatis-plugin<BR>
 * File name: CommonUtil.java <BR>
 * Author: guich <BR>
 * Project: mybatis-plugin <BR>
 * Version: v 1.0 <BR>
 * Date: 2018/4/9 13:10 <BR>
 * Description: <BR>
 * Function List:  <BR>
 */
public class EntityVO {
    private String packageName;
    private String className;
    private String table;
    private List<ColumnVO> attributes;
    private List<String> importPackages;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<ColumnVO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ColumnVO> attributes) {
        this.attributes = attributes;
    }

    public List<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(List<String> importPackages) {
        this.importPackages = importPackages;
    }
}
