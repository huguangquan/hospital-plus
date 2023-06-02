package com.plus.hospital.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * springboot application 启动类
 * @author hgq
 */
@SpringBootApplication
@Configuration
@EnableDiscoveryClient
public class UsercenterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercenterWebApplication.class, args);
    }

}
