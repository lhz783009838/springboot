package com.springboot.starter.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.springboot.starter.common.exception.SystemException;
import com.springboot.starter.dao.SysUserDao;
import com.springboot.starter.entity.authority.SysUser;
import com.springboot.starter.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author baker
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = this.baseMapper.selectDetailByUserName(s);
        if (null == sysUser) {
            throw new UsernameNotFoundException("找不到用户");
        }
        return sysUser;
    }

    @Override
    public SysUser addUser(SysUser sysUser) {
        final String userName = sysUser.getUserName();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (null != this.selectOne(new EntityWrapper<SysUser>().eq("user_name", userName))) {
            throw new SystemException("用户名已存在");
        }
        final String rawPassword = sysUser.getPassword();
        sysUser.setPassword(encoder.encode(rawPassword));
        this.insert(sysUser);
        return sysUser;
    }
}
