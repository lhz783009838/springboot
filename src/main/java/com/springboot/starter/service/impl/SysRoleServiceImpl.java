package com.springboot.starter.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.springboot.starter.dao.SysRoleDao;
import com.springboot.starter.entity.authority.SysRole;
import com.springboot.starter.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * @author baker
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
}
