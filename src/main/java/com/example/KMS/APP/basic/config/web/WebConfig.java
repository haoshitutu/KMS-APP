package com.example.KMS.APP.basic.config.web;

import com.example.KMS.APP.basic.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description web配置
 * @Author tutu
 * @Date 2020/8/14
 * @Version 1.0
 **/
@Component
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加静态资源文件，外部可以直接访问
     * @param registration
     */
    public void addResourceHandlers(ResourceHandlerRegistration registration){
        registration.addResourceLocations("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
