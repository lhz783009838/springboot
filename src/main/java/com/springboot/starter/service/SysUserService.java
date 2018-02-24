package com.springboot.starter.service;

import com.baomidou.mybatisplus.service.IService;
import com.springboot.starter.entity.authority.SysUser;

/**
 * @author baker
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 添加用户（用户注册）
     * @param sysUser 注册信息
     * @return 用户
     */
    SysUser addUser(SysUser sysUser);
}
