package com.xiaogch.common;

public class ColumnVO {
    private String column;
    private String name;
    private String methodNameSuffix;
    private String type;
    private String dataType;
    private String comment;
    private Integer maxLength;
    private Boolean hasDefault;
    private Boolean nullable;
    private String columnKey;
    private Boolean id;
    private String extra;
    private Boolean autoIncrement;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodNameSuffix() {
        return methodNameSuffix;
    }

    public void setMethodNameSuffix(String methodNameSuffix) {
        this.methodNameSuffix = methodNameSuffix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean getHasDefault() {
        return hasDefault;
    }

    public void setHasDefault(Boolean hasDefault) {
        this.hasDefault = hasDefault;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Boolean getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ColumnVO{");
        sb.append("column='").append(column).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", methodNameSuffix='").append(methodNameSuffix).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", dataType='").append(dataType).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", maxLength=").append(maxLength);
        sb.append(", hasDefault=").append(hasDefault);
        sb.append(", nullable=").append(nullable);
        sb.append(", columnKey='").append(columnKey).append('\'');
        sb.append(", id=").append(id);
        sb.append(", extra='").append(extra).append('\'');
        sb.append(", autoIncrement=").append(autoIncrement);
        sb.append('}');
        return sb.toString();
    }
}
