package com.xiaogch.common;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.File;
import static java.io.File.separator;

/**
 * ProjectName: mybatis-plugin<BR>
 * File name: CommonUtil.java <BR>
 * Author: guich <BR>
 * Project: mybatis-plugin <BR>
 * Version: v 1.0 <BR>
 * Date: 2018/4/9 14:36 <BR>
 * Description: <BR>
 * Function List:  <BR>
 */
public class FreemakerUtil {

    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
    static  {
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setClassForTemplateLoading(FreemakerUtil.class , "/templates");
    }
    public static Configuration getConfiguration() {
        return configuration;
    }

    public static Template getEntityTemplate() throws IOException {
        return getConfiguration().getTemplate("entity.ftl");
    }

    public static Template getDaoTemplate() throws IOException {
        return getConfiguration().getTemplate("dao.ftl");
    }

    private static Template getServiceTemplate() throws Exception {
        return getConfiguration().getTemplate("service.ftl");
    }

    private static Template getServiceImplTemplate() throws Exception {
        return getConfiguration().getTemplate("serviceImpl.ftl");
    }

    private static Template getMapperTemplate() throws Exception {
        return getConfiguration().getTemplate("mapper.ftl");
    }


    public static void main(String[] args) throws Exception {

        BaseConfigureVO baseConfigureVO = new BaseConfigureVO();
        baseConfigureVO.setDriver("com.mysql.jdbc.Driver");
        baseConfigureVO.setUrl("jdbc:mysql://192.168.1.232:3306/information_schema?useUnicode=true&characterEncoding=UTF-8");
        baseConfigureVO.setTable("mkt_banner_info");
        baseConfigureVO.setPassword("ca3@z6Yx");
        baseConfigureVO.setUserName("reserve_all");
        DatabaseDao databaseDao = new DatabaseDao(baseConfigureVO);
        List<ColumnVO> columnVOS = databaseDao.getColumnList();
        Map<String , Object> model = new HashMap<String, Object>();
        model.put("table" , baseConfigureVO.getTable());
        String temp;
        if (baseConfigureVO.getTable().startsWith("t_")) {
            temp = baseConfigureVO.getTable().substring(2);
        } else {
            temp = baseConfigureVO.getTable();
        }
        temp = DatabaseDao.underlineToCamel(temp);
        model.put("entityInstanceName" , temp);
        model.put("entityClassName" , temp.substring(0 , 1).toUpperCase() + temp.substring(1));


        model.put("basePackage" , "com.sunland.legion8th");
        model.put("attributes" , columnVOS);

        String basePath = "F:\\code-generator\\src";
        mkdir(basePath , (String) model.get("basePackage"));

        createEntityFile(model , basePath , (String) model.get("basePackage"));
        createDaoFile(model , basePath , (String) model.get("basePackage"));
        createMapperFile(model , basePath , (String) model.get("basePackage"));
        createServiceFile(model , basePath , (String) model.get("basePackage"));

    }


    public static void mkdir(final String basePath , final String packageName) {
        String[] temp = packageName.split("\\.");
        String tempPath = basePath ;
        for (String str : temp) {
            File file = new File(tempPath + separator + str);
            if (!file.exists()) {
                boolean result = file.mkdir();
                System.out.println(tempPath + separator + str + " create " + (result ? "success" : "failure"));
            } else {
                System.out.println(tempPath + separator + str + " is exists !");
            }
            tempPath = tempPath + separator + str;
        }
    }

    public static void createEntityFile(Map<String , Object> model , final String basePath , final String packageName) throws Exception {
        mkdir(basePath , packageName + ".entity");
        String[] temp = packageName.split("\\.");
        String path = basePath;
        for (String str : temp) {
            path =path + separator + str;
        }
        path = path + separator + "entity" + separator +  model.get("entityClassName") + ".java";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        Template template = getEntityTemplate();
        template.process(model , printWriter);
        printWriter.flush();
        printWriter.close();
        fileOutputStream.close();
    }

    public static void createDaoFile(Map<String , Object> model , final String basePath , final String packageName) throws Exception {

        mkdir(basePath , packageName + ".dao");
        String[] temp = packageName.split("\\.");
        String path = basePath;
        for (String str : temp) {
            path =path + separator + str;
        }
        path = path + separator + "dao" + separator +  model.get("entityClassName") + "Dao.java";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        Template template = getDaoTemplate();
        template.process(model , printWriter);
        printWriter.flush();
        printWriter.close();
        fileOutputStream.close();
    }

    public static void createServiceFile(Map<String , Object> model , final String basePath , final String packageName) throws Exception {

        mkdir(basePath , packageName + ".service");
        String[] temp = packageName.split("\\.");
        String path = basePath;
        for (String str : temp) {
            path =path + separator + str;
        }
        path = path + separator + "service" + separator +  model.get("entityClassName") + "Service.java";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        Template template = getServiceTemplate();
        template.process(model , printWriter);
        printWriter.flush();
        printWriter.close();
        fileOutputStream.close();

        createServiceImplFile(model , basePath , packageName);

    }

    public static void createServiceImplFile(Map<String , Object> model , final String basePath , final String packageName) throws Exception {
        mkdir(basePath , packageName + ".service.impl");
        String[] temp = packageName.split("\\.");
        String path = basePath;
        for (String str : temp) {
            path =path + separator + str;
        }
        path = path + separator + "service" + separator + "impl" + separator + model.get("entityClassName") + "ServiceImpl.java";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        Template template = getServiceImplTemplate();
        template.process(model , printWriter);
        printWriter.flush();
        printWriter.close();
        fileOutputStream.close();
    }

    public static void createMapperFile(Map<String , Object> model , final String basePath , final String packageName) throws Exception {
        mkdir(basePath , packageName + ".dao");
        String[] temp = packageName.split("\\.");
        String path = basePath;
        for (String str : temp) {
            path =path + separator + str;
        }
        path = path + separator + "dao" + separator + model.get("entityClassName") + "Dao.xml";
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        Template template = getMapperTemplate();
        template.process(model , printWriter);
        printWriter.flush();
        printWriter.close();
        fileOutputStream.close();
    }
}

