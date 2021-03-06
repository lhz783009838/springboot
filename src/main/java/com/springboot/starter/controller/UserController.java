package com.springboot.starter.controller;

import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.entity.authority.SysUser;
import com.springboot.starter.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

/**
 * @author linhuanzhen
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private SysUserService sysUserService;

    public UserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping(value = "/save")
    public DataResult addUser(@Valid @RequestBody SysUser sysUser) {
        SysUser user = sysUserService.addUser(sysUser);
        if (null == user) {
            return DataResult.fail("用户名已被注册");
        }
        return DataResult.success("注册成功");
    }
}
