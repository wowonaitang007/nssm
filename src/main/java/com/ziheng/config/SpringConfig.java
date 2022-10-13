package com.ziheng.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//配置spring 来管理数据层和业务层的bean
@Configuration
@ComponentScan({"com.ziheng.dao", "com.ziheng.service"})
//spring加载外部配置文件
@PropertySource("classpath:jdbc.properties")
//开启事务
@EnableTransactionManagement
//导入第三方配置
@Import({jdbcConfig.class, mybatisConfig.class})
public class SpringConfig {
}
