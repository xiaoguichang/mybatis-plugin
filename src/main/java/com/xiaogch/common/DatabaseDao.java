package com.xiaogch.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DatabaseDao {

    private BaseConfigureVO baseConfigureVO;

    public DatabaseDao(BaseConfigureVO baseConfigureVO) {
        this.baseConfigureVO = baseConfigureVO;
    }

    public List<ColumnVO> getColumnList() {


        try {
            Class.forName(baseConfigureVO.getDriver());
            Connection connection = DriverManager.getConnection(baseConfigureVO.getUrl() , baseConfigureVO.getUserName(), baseConfigureVO.getPassword());
            connection.prepareStatement("");
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        DriverManager driverManager = DriverManager.


        return Collections.EMPTY_LIST;
    }
}
