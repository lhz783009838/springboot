package com.springboot.starter.service;

import com.springboot.starter.entity.authority.SysUser;

/**
 * @author baker
 */
public interface AuthService {

    /**
     * 用户注册
     * @param sysUser 用户信息
     * @return 用户信息
     */
    SysUser register(SysUser sysUser);

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @return token
     */
    String login(String userName, String password);

    /**
     * 更新token
     * @param oldToken　旧token
     * @return 新token
     */
    String refresh(String oldToken);
}
