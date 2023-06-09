package com.plus.hospital.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plus.hospital.framework.core.bean.response.ResponseResult;
import com.plus.hospital.framework.core.constants.SystemConstants;
import com.plus.hospital.framework.core.enums.ErrorCode;
import com.plus.hospital.framework.core.enums.PlatformEnum;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

/**
 * 登录验证filter
 *
 * @author huguangquan
 * 2023/6/9
 **/
@Slf4j
public class LoginAuthFilter implements GlobalFilter, Ordered {

    private static final String router_exclude_key = "exclude-auth-uri";

    private static final String content_type_json = "application/json;charset=UTF-8";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getPath();

        // 1. 验证请求头是否包含platform，并且是合法
        List<String> platform = request.getHeaders().get(SystemConstants.platform_header_name);
        if (CollectionUtils.isEmpty(platform) || PlatformEnum.getPlatform(platform.get(0)) == null) {
            log.warn("请求={}，platform={}，平台不合法。", uri, CollectionUtils.isEmpty(platform) ? "null" : platform.get(0));
            return responseInvalidate(response);
        }

        // 2. 判断是否auth login，调用sa-token组件进行登录验证； 如果放行url直接执行后续filter
        Route route = (Route) exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        HashMap<String, String> excludeUriMap = (HashMap<String, String>)route.getMetadata().get(router_exclude_key);
        if (!CollectionUtils.isEmpty(excludeUriMap) && excludeUriMap.values().contains(uri)) {
            return chain.filter(exchange);
        }

        if (!StpUtil.isLogin()) {
            // 未登录返回错误提示
            log.warn("请求={}，未登录被拦截处理。", uri);
            return responseInvalidate(response);
        }

        // 3. auth login通过的请求，在请求头追加当前登录的accountId
        ServerHttpRequest temp = request.mutate()
                .header(SystemConstants.login_account_header_name, StpUtil.getLoginId().toString()).build();
        return chain.filter(exchange.mutate().request(temp).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private Mono<Void> responseInvalidate(ServerHttpResponse response) throws JsonProcessingException {
        ResponseResult responseResult = new ResponseResult();
        responseResult.failure(ErrorCode.forbidden);
        byte[] bytes = objectMapper.writeValueAsBytes(responseResult);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, content_type_json);
        return response.writeWith(Mono.just(buffer));
    }
}
