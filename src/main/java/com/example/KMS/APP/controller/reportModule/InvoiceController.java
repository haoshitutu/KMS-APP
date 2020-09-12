package com.example.KMS.APP.controller.reportModule;

import com.example.KMS.APP.utils.FreemarkerUtil;
import com.example.KMS.APP.utils.PDFUtil;
import com.lowagie.text.DocumentException;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Description 发票
 * @Author tutu
 * @Date 2020/9/9
 * @Version 1.0
 **/
@Api(tags = "报表模块--发票控制层")
@Validated
@Controller
@RequestMapping(value = "v1/invoice")
public class InvoiceController {

    //发票模板管理

    //根据模板导出PDF格式发票
    @ApiOperation("导出fitbit发票")
    @RequestMapping(value = "/dowloadFitbitInvoice")
    public void dowloadFitbitInvoice(Map<String,Object> dataMap,
                                     HttpServletRequest request, HttpServletResponse response)
            throws IOException, TemplateException, DocumentException {
        String filePath = InvoiceController.class.getResource("/").getPath();
        String url = request.getRequestURL().toString()
                .replaceAll("/v1/invoice/dowloadFitbitInvoice", "");
        dataMap.put("basePath", url); //用于访问静态资源
        //--------------------- 生成HTML文件（开始）-------------------------//
        //模板路径
        String ftlPath = filePath + "template/";
        String ftlName = "fitbitInvoice.ftl";

        //生成随机的HTML文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        String htmlFilePath = filePath + "document/" + dateStr + "-" + "fitbitInvoice.html";
        FreemarkerUtil.ftlToHtml(ftlPath, ftlName, htmlFilePath, dataMap);
        //--------------------- 生成HTML文件（结束）-------------------------//

        //--------------------- 生成PDF文件（开始）-------------------------//
        String pdfFilePath = filePath + "document/" + dateStr + "-" + "fitbitInvoice.pdf";
        PDFUtil.htmlToPDF(htmlFilePath, pdfFilePath);
        //--------------------- 生成PDF文件（结束）-------------------------//

        //--------------------- 本地下载（开始）-------------------------//
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Cache-Control", "public");
        response.setHeader("Content-Disposition", "attachment;filename=" + dateStr + "-" + "fitbitInvoice.pdf");

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(pdfFilePath);
        int read = 0;
        while ((read = in.read()) != -1 && in != null) {
            out.write(read);
        }
        out.flush();
        in.close();
        out.close();

        File htmlFile = new File(htmlFilePath);
        if (htmlFile.exists()) htmlFile.delete();

        File pdfFile = new File(pdfFilePath);
        if (pdfFile.exists()) pdfFile.delete();
        //--------------------- 本地下载（结束）-------------------------//
    }


}
