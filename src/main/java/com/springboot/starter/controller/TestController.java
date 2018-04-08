package com.springboot.starter.controller;

import com.springboot.starter.common.handle.BaseController;
import com.springboot.starter.common.properties.DataSourceProperties;
import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.entity.authority.SysRole;
import com.springboot.starter.entity.authority.SysUser;
import com.springboot.starter.entity.authority.SysUserRole;
import com.springboot.starter.service.SysRoleService;
import com.springboot.starter.service.SysUserRoleService;
import com.springboot.starter.service.SysUserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author linhuanzhen
 */
@RestController
@RequestMapping(value = "/test")
public class TestController extends BaseController {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    private SysUserService sysUserService;

    private SysRoleService sysRoleService;

    public TestController(SysUserService sysUserService, SysRoleService sysRoleService, SysUserRoleService sysUserRoleService) {
        this.sysUserService = sysUserService;
        this.sysRoleService = sysRoleService;
    }

    @GetMapping(value = "/error")
    public String error() {
        int a = 1, b = 0;
        int c = a / b;
        return "success";
    }

    @GetMapping(value = "/hello")
    public DataResult hello() {
        return DataResult.success();
    }

    @PostMapping(value = "/bindUserRole")
    public DataResult bindUserRole(@RequestBody Map param) {
        Long uid = MapUtils.getLong(param, "userId");
        Long rId = MapUtils.getLong(param, "roleId");
        SysUser user = sysUserService.selectById(uid);
        SysRole role = sysRoleService.selectById(rId);
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        userRole.insert();
        return DataResult.success("绑定成功");
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value = "/securityRole")
    public DataResult hasRoleAdmin() {
        return DataResult.success("访问成功");
    }
}
