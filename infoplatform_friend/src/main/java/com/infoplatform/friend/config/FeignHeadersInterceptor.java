package com.infoplatform.friend.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * *使得feign在调用别的服务端的时候 能够把自己header头里面的参数带过去
 *
 * @author 15293
 * @since 14:53 2019/8/19
 **/
@Configuration
public class FeignHeadersInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest request = getHttpServletRequest();
        if (Objects.isNull(request)) {
            return;
        }

        Map<String, String> headers = getHeaders(request);
        if (headers.size() > 0) {
            for (Entry<String, String> entry : headers.entrySet()) {
                // 把请求过来的header请求头 原样设置到feign请求头中 包括token
                template.header(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 获得当前请求
     **/
    private HttpServletRequest getHttpServletRequest() {
        try {
            // 这种方式获取的HttpServletRequest是线程安全的
            return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得请求的请求头
     **/
    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}