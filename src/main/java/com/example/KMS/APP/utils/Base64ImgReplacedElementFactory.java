package com.example.KMS.APP.utils;

import org.w3c.dom.Element;
import com.lowagie.text.pdf.codec.Base64;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import java.io.IOException;

/**
 * @Description 图片base64支持，把html图片转换为itext的图片对象
 * @Author tutu
 * @Date 2020/9/12
 * @Version 1.0
 **/
public class Base64ImgReplacedElementFactory implements ReplacedElementFactory {

    /**
     * 实现替换html中的img标签
     * @param layoutContext 上下文
     * @param blockBox 盒子
     * @param userAgentCallback 回调
     * @param width css宽
     * @param height css高
     * @return 替换之后的元素
     */
    public ReplacedElement createReplacedElement(LayoutContext layoutContext, BlockBox blockBox, UserAgentCallback userAgentCallback, int width, int height) {
        Element e = blockBox.getElement();
        if (e == null) {
            return null;
        }
        String nodeName = e.getNodeName();
        // 找到img标签
        if (nodeName.equals("img")) {
            String attribute = e.getAttribute("src");
            FSImage fsImage;
            try {
                // 生成itext图像
                fsImage = buildImage(attribute, userAgentCallback);
            } catch (BadElementException e1) {
                fsImage = null;
            } catch (IOException e1) {
                fsImage = null;
            }
            if (fsImage != null) {
                // 对图像进行缩放
                if (width != -1 || height != -1) {
                    fsImage.scale(width, height);
                }
                return new ITextImageElement(fsImage);
            }
        }
        return null;
    }

    /**
     * 编解码base64并生成itext图像
     *
     */
    protected FSImage buildImage(String srcAttr, UserAgentCallback uac) throws IOException, BadElementException {

        FSImage fiImg = null;
        if (srcAttr.toLowerCase().startsWith("data:image/")) {
            String base64Code = srcAttr.substring(srcAttr.indexOf("base64,") + "base64,".length(), srcAttr.length());
            // 解码
            byte[] decodedBytes = Base64.decode(base64Code);
            fiImg = new ITextFSImage(Image.getInstance(decodedBytes));
        } else {
            fiImg = uac.getImageResource(srcAttr).getImage();
        }
        return fiImg;
    }

    public void reset() {

    }

    @Override
    public void remove(Element element) {

    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener formSubmissionListener) {

    }
}
