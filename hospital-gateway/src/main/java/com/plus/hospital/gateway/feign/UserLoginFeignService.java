package com.plus.hospital.gateway.feign;

import com.plus.hospital.gateway.feign.fallback.UserLoginFeignServiceFallback;
import com.plus.hospital.usercenter.api.LoginServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户中心登录feign-client
 *
 * @author huguangquan
 * 2023/6/2
 **/
@FeignClient(value = "${feign.name.usercenter}", fallback = UserLoginFeignServiceFallback.class)
public interface UserLoginFeignService extends LoginServiceApi {
}
