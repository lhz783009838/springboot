package com.springboot.starter.service;

import com.baomidou.mybatisplus.service.IService;
import com.springboot.starter.entity.authority.SysUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author linhuanzhen
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户名校验
     * @param s 用户名
     * @return 用户详情
     */
    UserDetails loadUserByUsername(String s);
    /**
     * 添加用户（用户注册）
     * @param sysUser 注册信息
     * @return 用户
     */
    SysUser addUser(SysUser sysUser);
}
