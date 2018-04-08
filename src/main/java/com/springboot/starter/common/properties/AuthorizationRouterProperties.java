package com.springboot.starter.common.properties;

/**
 * @author linhuanzhen
 * spring security 路由配置
 */
public class AuthorizationRouterProperties {

    private String login;

    private String logout;

    private String refresh;

    private String register;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }
}
