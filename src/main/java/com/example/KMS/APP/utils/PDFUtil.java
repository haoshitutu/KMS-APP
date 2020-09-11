package com.example.KMS.APP.utils;

import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description PDF生成器
 * @Author tutu
 * @Date 2020/9/8
 * @Version 1.0
 **/
public class PDFUtil {
    public static void main(String[] args) throws IOException, DocumentException {

        // 1.新建document对象
        Document document = new Document();

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/daoba/Desktop/test.pdf"));

        // 3.打开文档
        document.open();

        // 4.添加一个内容段落
        //使用系统的字体
        BaseFont sysFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(sysFont, 12, Font.NORMAL);
        document.add(new Paragraph("你好，世界",font));

        // 5.关闭文档
        document.close();

    }

    /**
     * html文件转换为pdf文件
     * @param htmlFilePath html文件路径
     * @param pdfFilePath pdf文件路径
     */
    public static void htmlToPDF(String htmlFilePath, String pdfFilePath) throws IOException, com.lowagie.text.DocumentException {
        OutputStream os = new FileOutputStream(pdfFilePath);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(htmlFilePath);

        //如果html文件携带图片，需要将图片标签转换为itext自己的图片对象
        //renderer.getSharedContext().setReplacedElementFactory(new Base64Image);

        //解决中文显示问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
       // String fontsPath =  PDFUtil.class.getResource("/systemFonts").getPath();
        fontResolver.addFont("/systemFonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        //转换
        renderer.layout();
        renderer.createPDF(os);

        os.flush();
        os.close();
    }
}
