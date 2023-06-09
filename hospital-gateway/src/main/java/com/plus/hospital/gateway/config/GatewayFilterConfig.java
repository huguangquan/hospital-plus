package com.plus.hospital.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import com.plus.hospital.gateway.filter.LoginAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关filter配置
 * @author huguangquan
 * 2023/6/9
 **/
@Configuration
public class GatewayFilterConfig {

    @Bean
    public SaReactorFilter saReactorFilter(){
        return new SaReactorFilter();
    }

    @Bean
    public LoginAuthFilter loginAuthFilter() {
        return new LoginAuthFilter();
    }
}
