package com.infoplatform.gateway.config;

import com.api.common.constant.CommonConstant;
import com.api.common.enums.StatusCode;
import com.api.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

/**
 * * 网关的过滤器类 用于进行签名校验
 *
 * @author 15293
 * @since 15:42 2019/8/9
 **/
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Value("${auth.skip.urls}")
    private String[] skipAuthUrls;

    @Autowired
    private RedisTemplate stringRedisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public int getOrder() {
        return -100;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if (Arrays.asList(skipAuthUrls).contains(url)
                || url.startsWith("/m")) return chain.filter(exchange);
        if (HttpMethod.OPTIONS.equals(exchange.getRequest().getMethod())) return chain.filter(exchange);

        //从请求头中取出token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        //未携带token或token在黑名单内
        if (token == null || token.isEmpty() || isBlackToken(token))
            return getVoidMono(exchange, StatusCode.USERNOTLOGIN);

        //取出token包含的身份，用于业务处理
        Claims claims;
        try {
            claims = jwtUtil.parseJWT(token);
        } catch (ExpiredJwtException e) {
            return getVoidMono(exchange, StatusCode.EXPIRETOKEN);
        }
        if (claims.isEmpty()) return getVoidMono(exchange, StatusCode.INVALIDPARAMS);

        //todo 对特定url 进行权限的拦截
        String roles = String.valueOf(claims.get("roles"));
        if (StringUtils.isNotBlank(roles)) {
            if (Arrays.asList(roles).contains("admin")) {

            }
        }

        //将现在的request，添加当前身份
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header("Authorization-UserName", new String[]{claims.getSubject()}).
                header("Authorization-UserId", new String[]{claims.getId()}).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    /**
     * 封装
     **/
    private Mono<Void> getVoidMono(ServerWebExchange exchange, StatusCode statusCode) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        originalResponse.setStatusCode(HttpStatus.OK);
        originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String s = "{\"code\": \"" + statusCode.getCode() + ",\"msg\": " + statusCode.getMsg() + "}";
        byte[] response = s.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
        return originalResponse.writeWith(Flux.just(buffer));
    }

    /**
     * 判断token是否在黑名单内
     *
     * @param token
     * @return
     */
    private boolean isBlackToken(String token) {
        assert token != null;
        return stringRedisTemplate.hasKey(CommonConstant.REDISSTOREPREFIX + "BLACKTOKENLIST:" + token);
    }
}
