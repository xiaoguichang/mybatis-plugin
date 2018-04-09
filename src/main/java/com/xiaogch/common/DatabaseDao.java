package com.xiaogch.common;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DatabaseDao {
    private BaseConfigureVO baseConfigureVO;

    public DatabaseDao(BaseConfigureVO baseConfigureVO) {
        this.baseConfigureVO = baseConfigureVO;
    }

    public List<ColumnVO> getColumnList() {
        List<ColumnVO> columnVOS = new ArrayList<ColumnVO>();
        try {
            Class.forName(baseConfigureVO.getDriver());
            Connection connection = DriverManager.getConnection(baseConfigureVO.getUrl() , baseConfigureVO.getUserName(), baseConfigureVO.getPassword());
            PreparedStatement statement = connection.prepareStatement(
                    "select a.column_name , a.column_comment , a.column_default , a.is_nullable , " +
                                "a.data_type , a.character_maximum_length , a.column_key , a.extra " +
                            " from columns a where a.table_name = ? order by a.ordinal_position");
            statement.setString(1 , baseConfigureVO.getTable());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                ColumnVO columnVO = new ColumnVO();
                columnVO.setColumn(resultSet.getString(1));
                columnVO.setComment(resultSet.getString(2));
                columnVO.setHasDefault(resultSet.getString(3) != null);
                columnVO.setNullable("YES".equalsIgnoreCase(resultSet.getString(4)));
                columnVO.setDataType(resultSet.getString(5));
                columnVO.setType(typeMap.get(columnVO.getDataType()));
                columnVO.setMaxLength(resultSet.getInt(6));
                columnVO.setColumnKey(resultSet.getString(7));
                columnVO.setId("pri".equalsIgnoreCase(columnVO.getColumnKey()));
                columnVO.setExtra(resultSet.getString(8));
                columnVO.setAutoIncrement("auto_increment".equals(columnVO.getExtra()));
                columnVO.setName(underlineToCamel(columnVO.getColumn()));
                columnVO.setMethodNameSuffix(columnVO.getName().substring(0, 1).toUpperCase() + columnVO.getName().substring(1));
                columnVOS.add(columnVO);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return columnVOS;
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public static final char UNDERLINE='_';

    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String underlineToCamel2(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }

    private static Map<String , String> typeMap = new HashMap<String, String>();

    static {

        typeMap.put("tinyint" , Integer.class.getSimpleName());
        typeMap.put("smallint" , Integer.class.getSimpleName());
        typeMap.put("mediumint" , Integer.class.getSimpleName());
        typeMap.put("int" , Integer.class.getSimpleName());
        typeMap.put("integer" , Integer.class.getSimpleName());
        typeMap.put("bigint" , Integer.class.getSimpleName());

        typeMap.put("decimal" , Double.class.getSimpleName());
        typeMap.put("numeric" , Double.class.getSimpleName());
        typeMap.put("float" , Float.class.getSimpleName());
        typeMap.put("double" , Double.class.getSimpleName());

        typeMap.put("char" , String.class.getSimpleName());
        typeMap.put("varchar" , String.class.getSimpleName());
        typeMap.put("tinytext" , String.class.getSimpleName());
        typeMap.put("text" , String.class.getSimpleName());
        typeMap.put("mediumtext" , String.class.getSimpleName());
        typeMap.put("enum" , String.class.getSimpleName());
        typeMap.put("set" , String.class.getSimpleName());

        typeMap.put("timestamp" , Date.class.getSimpleName());
        typeMap.put("datetime" , Date.class.getSimpleName());
        typeMap.put("date" , Date.class.getSimpleName());
        typeMap.put("year" , Integer.class.getSimpleName());
    }

//    public static void main(String[] args) {
//        System.out.println("qwerty".substring(0, 1).toUpperCase() + "qwerty".substring(1));
//        System.out.println(underlineToCamel("mkt_banner_info"));
//        System.out.println(underlineToCamel2("mkt_banner_info"));
//    }
}
