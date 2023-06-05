package com.plus.hospital.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.plus.hospital.gateway.feign")
@EnableDiscoveryClient
public class HospitalGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalGatewayApplication.class, args);
    }

}
