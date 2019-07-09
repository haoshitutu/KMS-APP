package com.example.KMS.APP.basic.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Description 数据库连接配置类
 * @Author cx
 * @Date 2019/6/11 11:43
 * @Version 1.0
 **/
@Configuration //标记为配置类
public class DbConfig {

    //读取配置文件中的配置地址
    @Value("${jdbc_url}")
    private String jdbcUrl;

    @Value("${jdbc_user}")
    private String jdbcUser;

    @Value("${jdbc_password}")
    private String jdbcPwd;

    //定义数据库连接池类
    @Bean("dataSource")
    @Primary //标记为单例
    public DataSource dataSource() {
        //从URL中自动识别驱动类
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPwd);

        dataSource.setMaxActive(20); //最大连接池数
        dataSource.setMinIdle(1); //最小连接池数
        dataSource.setInitialSize(20); //初始化连接数
        dataSource.setMaxWait(60000); //最长等待时间毫秒

        dataSource.setTimeBetweenEvictionRunsMillis(60000); //销毁线程检测时间间隔
        dataSource.setMinEvictableIdleTimeMillis(30000);

        dataSource.setValidationQuery("select * from dual"); //用来检测连接是否有效的SQL
        dataSource.setTestWhileIdle(true); //申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis,执行validationQuery

        return dataSource;
    }

    //定义事务类
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}