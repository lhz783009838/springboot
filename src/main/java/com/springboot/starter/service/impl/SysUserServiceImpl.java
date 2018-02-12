package com.springboot.starter.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.springboot.starter.dao.SysUserDao;
import com.springboot.starter.entity.authority.SysUser;
import com.springboot.starter.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author baker
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService, UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
