package com.example.KMS.APP.basic.config.db;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Description mybatis Mapper 配置
 * @Author cx
 * @Date 2019/7/8 18:22
 * @Version 1.0
 **/
@Configuration
public class MapperConfig {

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource")DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //entity所在包
        bean.setTypeAliasesPackage("com.example.KMS.APP.entity");
        //分页插件
        PageInterceptor pageHelper = new PageInterceptor();
        bean.setPlugins(new Interceptor[] {pageHelper});
        //XML 目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        } catch (Exception e) {}

        try {
            return bean.getObject();
        } catch (Exception e) {
            throw new IllegalArgumentException("org.mybatis.spring.SqlSessionFactoryBean init error", e);
        }
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //配置多个时，需要以分号结尾
        mapperScannerConfigurer.setBasePackage("com.example.KMS.APP.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}