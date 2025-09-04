package com.drx.starter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.drx.starter.mapper")
public class MybatisConfig {
}
