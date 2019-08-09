package com.api.common.util;

import com.typesafe.config.ConfigFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 鉴权Util
 *
 * @author 15293
 * @since 10:33 2019/8/9
 **/
@Data
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private String key;
    /**
     * 过期时间
     **/
    private long ttl;

    /**
     * 生成JWT
     *
     * @param id
     * @param subject 用户认证
     * @return
     */
    public String createJWT(String id, String subject, String roles) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(now).signWith(SignatureAlgorithm.HS256, key).claim("roles", roles);
        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + ttl));
        }

        return builder.compact();
    }

    /**
     * 解析JWT *
     *
     * @param jwtStr
     * @return
     */
    public Claims parseJWT(String jwtStr) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();
    }


    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoidHNzYiIsImlhdCI6MTU2NTM0MzE3OCwicm9sZXMiOiJkb2ciLCJleHAiOjE1NjUzNDM1Mzh9.pcdxaQuIUh_nwpy9bRZYLoTdo6rPMr8aXbzYn6-ySYo").getBody();
        System.err.println(claims.getSubject());
    }
}