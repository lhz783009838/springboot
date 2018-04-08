package com.springboot.starter.entity.authority;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linhuanzhen
 */
public class SysMenu extends Model<SysMenu>{

    private static final long serialVersionUID = 874259623886250233L;

    @TableId
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "permission")
    private String permission;

    @TableField(value = "url")
    private String url;

    @TableField(value = "method")
    private String method;

    @TableField(value = "parentId")
    private Long parentId;

    @TableField(value = "icon")
    private String icon;

    @TableField(value = "sort")
    private Integer sort;

    /** 菜单类型 0：叶节点 1：子节点 **/
    @TableField(value = "type")
    private Integer type;

    @TableField(value = "createUser")
    private Long userId;

    @TableField(value = "createTime")
    private Date createTime;

    @TableField(value = "updateUserId")
    private Long updateUserId;

    @TableField(value = "updateTime")
    private Date updateTime;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "remark")
    private String remark;

    /** 预留字段，前端组件字符串 **/
    @TableField(value = "component")
    private String component;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
