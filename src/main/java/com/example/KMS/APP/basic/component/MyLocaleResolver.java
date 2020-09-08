package com.example.KMS.APP.basic.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Description 区域解析器，从请求地址中获取
 * @Author tutu
 * @Date 2020/8/18
 * @Version 1.0
 **/
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 从请求头中获取语言的区域
     * 如果没有，则默认使用本地区域
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String lan = httpServletRequest.getParameter("lan");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(lan)) {
            String[] split = lan.split("_");
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
