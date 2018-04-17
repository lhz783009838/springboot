package com.springboot.starter.entity.authority;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author linhuanzhen
 * 角色菜单关联
 */
@TableName(value = "t_auth_role_menu")
public class SysRoleMenu extends Model<SysRoleMenu>{

    private static final long serialVersionUID = 480835154572420408L;

    @TableId
    private Long id;

    @TableField(value = "roleId")
    private Long roleId;

    @TableField(value = "menuId")
    private Long menuId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
