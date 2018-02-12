package com.springboot.starter.common.utils;

import com.springboot.starter.common.properties.AuthorizationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baker
 */
@Component
public class JwtTokenUtil implements Serializable{

    private static final long serialVersionUID = -8262718886678681081L;

    /** claim 用户信息 **/
    private static final String CLAIM_KEY_USERNAME = "CLAIM_KEY_USERNAME";

    /** claim 创建时间 **/
    private static final String CLAIM_KEY_CREATED = "CLAIM_KEY_CREATED";

    @Autowired
    private AuthorizationProperties properties;

    /**
     * 生成 token
     * @param userDetails 用户信息
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取claims
     * @param token token
     * @return claims
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(properties.getJwt().getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成token到期时间
     * @return 生成时间（ms）
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + properties.getJwt().getExpiration() * 1000);
    }

    /**
     * 获取token到期时间
     * @param token
     * @return
     */
    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFormToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 判断token是否到期
     * @param token token
     * @return 是否到期: true 到期; false 未到期
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    /**
     * 判断token创建时间是否早于修改密码时间
     * @param created token创建时间
     * @param lastPasswordReset 最后一次重置密码时间
     * @return true: 早于; false: 晚于
     */
    private boolean isCreateBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (null != lastPasswordReset && created.before(lastPasswordReset));
    }

    /**
     * 生成token
     * @param claims 用户信息
     * @return token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, properties.getJwt().getSecret())
                .compact();
    }
}
