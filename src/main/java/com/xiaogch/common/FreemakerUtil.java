package com.xiaogch.common;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

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

    public static Template getServiceTemplate() throws Exception {
        return getConfiguration().getTemplate("service.ftl");
    }

    public static Template getServiceImplTemplate() throws Exception {
        return getConfiguration().getTemplate("serviceImpl.ftl");
    }

    public static Template getMapperTemplate() throws Exception {
        return getConfiguration().getTemplate("mapper.ftl");
    }
}

