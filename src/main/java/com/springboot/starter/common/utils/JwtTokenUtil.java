package com.springboot.starter.common.utils;

import com.springboot.starter.common.properties.AuthorizationProperties;
import com.springboot.starter.entity.authority.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linhuanzhen
 */
@Component
public class JwtTokenUtil implements Serializable{

    private static final long serialVersionUID = -8262718886678681081L;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    /** claim 用户信息 **/
    private static final String CLAIM_KEY_USERNAME = "CLAIM_KEY_USERNAME";

    /** claim 创建时间 **/
    private static final String CLAIM_KEY_CREATED = "CLAIM_KEY_CREATED";

    private AuthorizationProperties properties;

    public JwtTokenUtil(AuthorizationProperties properties) {
        this.properties = properties;
    }

    /**
     * 根据token获取用户名
     * @param token token
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = (String) claims.get(CLAIM_KEY_USERNAME);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据token获取token创建时间
     * @param token token
     * @return 创建时间
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            logger.info("token异常，原因：{}",e.getMessage());
            created = null;
        }
        return created;
    }

    /**
     * 根据token获取token到期时间
     * @param token token
     * @return 到期时间
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 根据token获取Claims
     * @param token token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(properties.getJwt().getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.info("token异常，原因：{}",e.getMessage());
            claims = null;
        }
        logger.info("当前时间:     {}", DateFormatUtils.format(new Date(),"yyyy-dd-MM hh:mm:ss"));
        logger.info("token到期时间:{}",DateFormatUtils.format(claims.getExpiration(),"yyyy-dd-MM hh:mm:ss"));
        return claims;
    }

    /**
     * 生成token到期时间
     * @return 到时间
     */
    private Date generateExpirationDate() {
        logger.info("token生成时间:{}",DateFormatUtils.format(new Date(),"yyyy-dd-MM hh:mm:ss"));
        return new Date(System.currentTimeMillis() + properties.getJwt().getExpiration() * 1000);
    }

    /**
     * 判断token是否过期
     * @param token token
     * @return 是否到期 true:到期 false: 未到期
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 判断创建token时间是否早于密码重置时间
     * @param created token 创建时间
     * @param lastPasswordReset 密码重置时间
     * @return 是否 true:早于 false:晚于
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 生成token
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
     * 生成token
     * @param claims claims
     * @return token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, properties.getJwt().getSecret())
                .compact();
    }

    /**
     * 判断token是否可被刷新
     * @param token token
     * @param lastPasswordReset 密码重置时间
     * @return 是否 true:可以  false: 不可以
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token 原token
     * @return 新token
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            logger.info("token异常，原因：{}",e.getMessage());
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 判断token是否通过检验
     * @param token token
     * @param userDetails 用户信息
     * @return 是否通过检验 true:通过 false:不通过
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        SysUser user = (SysUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordRestTime()));
    }
}
