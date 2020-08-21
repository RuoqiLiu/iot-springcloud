package com.lrq;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.lrq.feign_service")
@MapperScan("com.lrq.dao")
public class UserServiceApplication {

    @Bean
    @ConfigurationProperties(prefix = "spring.mvc.datasource")
    public DataSource dateSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class);
    }
}