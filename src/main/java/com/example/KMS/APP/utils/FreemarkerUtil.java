package com.example.KMS.APP.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @Description html模板引擎
 * @Author tutu
 * @Date 2020/9/8
 * @Version 1.0
 **/
public class FreemarkerUtil {

    /**
     * 利用ftl模板生成html文件
     * @param ftlPath 模板文件的路径
     * @param ftlName 模板文件的名称
     * @param htmlFilePath 待生成的html文件（包含文件的路径和名称）
     * @param dataMap 数据
     */
    public static void ftlToHtml(String ftlPath, String ftlName, String htmlFilePath, Map<String, Object> dataMap)
            throws IOException, TemplateException {
        Configuration config = new Configuration();
        config.setDefaultEncoding("UTF-8");
        config.setDirectoryForTemplateLoading(new File(ftlPath));
        Template template = config.getTemplate(ftlName);

        template.process(dataMap, new FileWriter(htmlFilePath));
    }
}
