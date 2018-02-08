package com.springboot.starter.common.properties;

/**
 * @author baker
 */
public class JwtProperties {

    /**
     * token 加密头信息
     */
    private String header = "Authorization";

    /**
     * token 加密盐值
     */
    private String secret;

    /**
     * token 过期时间 默认未10分钟
     */
    private Integer expiration = 10;

    /**
     * token 头部值
     */
    private String tokenHead;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }
}
