package com.api.common.util;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.concurrent.ExecutionException;

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
     * @param id      用户Id
     * @param subject 用户名字
     * @param roles   用户角色
     * @param permits 用户权限
     * @return
     */
    public String createJWT(String id, String subject, String roles, String permits) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key).claim("roles", roles).claim("permits", permits);
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
    public Claims parseJWT(String jwtStr) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwtStr).getBody();
    }


    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoidHNzYiIsImlhdCI6MTU2NTM0MzE3OCwicm9sZXMiOiJkb2ciLCJleHAiOjE1NjUzNDM1Mzh9.pcdxaQuIUh_nwpy9bRZYLoTdo6rPMr8aXbzYn6-ySYo").getBody();
        System.err.println(claims.getSubject());
    }
}