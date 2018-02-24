package com.springboot.starter.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springboot.starter.entity.authority.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author baker
 */
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    SysUser selectDetailByUserName(@Param("userName") String userName);
}
