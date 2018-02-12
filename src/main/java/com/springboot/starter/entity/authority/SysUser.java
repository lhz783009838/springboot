package com.springboot.starter.entity.authority;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author baker
 */
@TableName(value = "t_auth_user")
public class SysUser extends Model<SysUser> implements UserDetails{

    private static final long serialVersionUID = -3403971025811324475L;
    @TableId
    private Long id;

    @TableField(value = "userName")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "lastPasswordRestTime")
    private Date lastPasswordRestTime;

    private List<SysRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastPasswordRestTime() {
        return lastPasswordRestTime;
    }

    public void setLastPasswordRestTime(Date lastPasswordRestTime) {
        this.lastPasswordRestTime = lastPasswordRestTime;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override
    protected Serializable pkVal() {
        return id;
    }

    /**
     * 获取角色权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authGrants = new ArrayList<>();
        for (SysRole role : this.getRoles()) {
            authGrants.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authGrants;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
