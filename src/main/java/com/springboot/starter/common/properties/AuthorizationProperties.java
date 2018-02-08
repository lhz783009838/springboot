package com.springboot.starter.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author baker
 * 安全配置信息读取器
 */
@ConfigurationProperties(prefix = "authorization.config")
public class AuthorizationProperties {

    private JwtProperties jwt = new JwtProperties();

    private AuthorizationRouterProperties router = new AuthorizationRouterProperties();

    public JwtProperties getJwt() {
        return jwt;
    }

    public void setJwt(JwtProperties jwt) {
        this.jwt = jwt;
    }

    public AuthorizationRouterProperties getRouter() {
        return router;
    }

    public void setRouter(AuthorizationRouterProperties router) {
        this.router = router;
    }
}
