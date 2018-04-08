package com.springboot.starter.entity.authority;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author linhuanzhen
 */
@TableName(value = "t_auth_user")
public class SysUser extends Model<SysUser> implements UserDetails{

    private static final long serialVersionUID = -3403971025811324475L;
    @TableId
    private Long id;

    @NotBlank(message = "请输入用户名")
    @TableField(value = "user_name")
    private String username;

    @NotBlank(message = "请输入密码")
    @TableField(value = "password")
    private String password;

    @TableField(value = "salt")
    private String salt;

    @TableField(value = "last_password_reset_time")
    private Date lastPasswordRestTime;

    @TableField(value = "sex")
    private Integer sex;

    @TableField(value = "birthday")
    private Date birthday;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "version")
    private Integer version;

    private transient List<SysRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if(null != this.getRoles()){
            for (SysRole role : this.getRoles()) {
                authGrants.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return authGrants;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
