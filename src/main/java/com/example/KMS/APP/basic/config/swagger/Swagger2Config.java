package com.example.KMS.APP.basic.config.swagger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @Description Swagger 配置
 * @Author cx
 * @Date 2019/6/11 11:40
 * @Version 1.0
 **/
@Configuration //标记为配置类
@EnableSwagger2 //开启在线接口文档
public class Swagger2Config {

    @Value("${swagger.host}")
    private String host;

    @Value("${swagger.enable}")
    private String swaggerEnable;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.contact.name}")
    private String contactName;

    @Value("${swagger.contact.email}")
    private String contactEmail;

    @Bean
    public Docket controllerApi() {
        boolean enableSwagger = StringUtils.isNotEmpty(swaggerEnable) && "true".equals(swaggerEnable);
        String protocol = "http";
        if (host.startsWith("https")) {
            protocol = "https";
        }
        host = host.replaceFirst("http[s]?://","");

        //构建文档的返回内容
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .apiInfo(new ApiInfoBuilder()
                    .title(title)
                        .contact(new Contact(contactName,"",contactEmail))
                        .version("Version:1.0")
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))  //文档扫描位置
                .paths(PathSelectors.any())
                .build()
                .protocols(Collections.singleton(protocol)).host(host);

    }


}