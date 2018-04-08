package com.springboot.starter.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.springboot.starter.common.utils.JwtTokenUtil;
import com.springboot.starter.entity.authority.SysUser;
import com.springboot.starter.service.AuthService;
import com.springboot.starter.service.SysUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author linhuanzhen
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private SysUserService sysUserService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, SysUserService sysUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.sysUserService = sysUserService;
    }

    @Override
    public SysUser register(SysUser sysUser) {
        final String userName = sysUser.getUsername();
        if (null != sysUserService.selectOne(new EntityWrapper<SysUser>().eq("user_name", userName))) {
            return null;
        }
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = sysUser.getPassword();
        sysUser.setPassword(encoder.encode(rawPassword));
        sysUserService.insert(sysUser);
        return sysUser;
    }

    @Override
    public String login(String userName, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userName, password);
        final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }
}
