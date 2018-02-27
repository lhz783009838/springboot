package com.springboot.starter.controller;

import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.entity.authority.SysUser;
import com.springboot.starter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baker
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public DataResult login(@RequestBody SysUser sysUser) {
        final String token = authService.login(sysUser.getUsername(), sysUser.getPassword());
        return DataResult.success("登录成功", token);
    }
}
