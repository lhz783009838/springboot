package com.springboot.starter.controller;

import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.entity.authority.SysRole;
import com.springboot.starter.service.SysRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linhuanzhen
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private SysRoleService sysRoleService;

    public RoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @PostMapping(value = "/save")
    public DataResult addRole(@RequestBody SysRole sysRole) {
        return DataResult.success("添加成功", sysRoleService.addRole(sysRole));
    }
}
