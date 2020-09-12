package com.example.KMS.APP.utils;

import com.itextpdf.text.*;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.RuntimeWorkerException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.charset.Charset;

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
     * 使用xhtmlrenderert将 html文件转换为pdf文件
     * @param htmlFilePath html文件路径
     * @param pdfFilePath pdf文件路径
     */
    public static void htmlToPDF(String htmlFilePath, String pdfFilePath) throws IOException, com.lowagie.text.DocumentException {
        OutputStream os = new FileOutputStream(pdfFilePath);
        ITextRenderer renderer = new ITextRenderer();

        //如果html文件携带图片，需要将图片标签转换为itext自己的图片对象
        //此处注意图片的格式不能是svg矢量图
        renderer.getSharedContext().setReplacedElementFactory(new Base64ImgReplacedElementFactory());
        renderer.getSharedContext().getTextRenderer().setSmoothingThreshold(0);

        //解决中文显示问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
       // String fontsPath =  PDFUtil.class.getResource("/systemFonts").getPath();
        fontResolver.addFont("/systemFonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        renderer.setDocument(htmlFilePath);

        //转换
        renderer.layout();
        renderer.createPDF(os);

        os.flush();
        os.close();
    }

    /**
     * 使用XMLWorker将 html文件转换为pdf文件
     * @param htmlFilePath html文件路径
     * @param pdfFilePath pdf文件路径
     */
    public static void html2pdf(String htmlFilePath, String pdfFilePath) throws IOException, RuntimeWorkerException {
        FileOutputStream fos = null;
        File filePdf = null;
        PdfWriter writer = null;
        Document document = null;
        try {
            filePdf = new File(pdfFilePath);
            fos = new FileOutputStream(filePdf);
            String htmls = readFile(htmlFilePath);
            document = new Document(PageSize.A4);
            document.setPageCount(1);
            writer = PdfWriter.getInstance(document, fos);
            document.open();
//            XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(){
//                @Override
//                public Font getFont(String fontname, String encoding, float size, int style) {
//                    return super.getFont(fontname == null ? "宋体" : fontname, encoding, size, style);
//                }
//            };
            // XML Worker
            XMLWorkerHelper worker =  XMLWorkerHelper.getInstance();
            worker.parseXHtml(writer, document,
                    new ByteArrayInputStream(htmls.getBytes("UTF-8")),
                    new ByteArrayInputStream(htmls.getBytes()),
                    Charset.forName("UTF-8"));
                    //fontProvider);
        } catch (DocumentException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            document.close();
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 读取Html
     * @param filePath 文件路径
     * @return 文件字符串
     */
    public static String readFile(String filePath) {
        String fileContent = "";
        try {
            File f = new File(filePath);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    //将读取到的字符拼接
                    fileContent += line;
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return fileContent;
    }

}
