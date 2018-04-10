package com.xiaogch.common;

public class BaseConfigureDTO {

    private String url;
    private String driver;
    private String password;
    private String userName;

    private String basePath;
    private String packageName;
    private String table;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseConfigureDTO{");
        sb.append("url='").append(url).append('\'');
        sb.append(", driver='").append(driver).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", basePath='").append(basePath).append('\'');
        sb.append(", packageName='").append(packageName).append('\'');
        sb.append(", table='").append(table).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
