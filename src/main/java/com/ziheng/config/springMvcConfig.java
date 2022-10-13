package com.ziheng.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//配置springmvc来管理controllen
@Configuration
@ComponentScan("com.ziheng.controller")
//开启json对象转换
@EnableWebMvc
public class springMvcConfig {
}
