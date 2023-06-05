package com.plus.hospital.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * springboot application 启动类
 * @author hgq
 */
@SpringBootApplication(scanBasePackages = "com.plus.hospital")
@Configuration
@EnableDiscoveryClient
@MapperScan("com.plus.hospital.usercenter.dao.mapper")
@EnableTransactionManagement
public class UsercenterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercenterWebApplication.class, args);
    }

}

