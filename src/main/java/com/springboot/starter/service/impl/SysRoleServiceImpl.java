package com.springboot.starter.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.springboot.starter.common.exception.SystemException;
import com.springboot.starter.dao.SysRoleDao;
import com.springboot.starter.entity.authority.SysRole;
import com.springboot.starter.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author linhuanzhen
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRole addRole(SysRole sysRole) {
        if(null != selectOne(new EntityWrapper<SysRole>().eq("name",sysRole.getName()))){
            throw new SystemException("角色名已存在");
        }
        insert(sysRole);
        return sysRole;
    }
}
