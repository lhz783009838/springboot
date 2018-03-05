package com.springboot.starter.service;

import com.baomidou.mybatisplus.service.IService;
import com.springboot.starter.entity.authority.SysRole;

/**
 * @author baker
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 添加角色
     * @param sysRole 添加角色
     * @return 角色信息
     */
    SysRole addRole(SysRole sysRole);
}
