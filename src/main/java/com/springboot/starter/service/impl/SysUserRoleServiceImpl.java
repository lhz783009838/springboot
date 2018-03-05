package com.springboot.starter.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.springboot.starter.dao.SysUserRoleDao;
import com.springboot.starter.entity.authority.SysUserRole;
import com.springboot.starter.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author baker
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

}
