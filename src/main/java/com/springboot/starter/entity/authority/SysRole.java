package com.springboot.starter.entity.authority;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linhuanzhen
 */
@TableName(value = "t_auth_role")
public class SysRole extends Model<SysRole>{

    private static final long serialVersionUID = 6714786296451717984L;

    @TableId
    private Long id;

    @NotBlank(message = "请输入角色名")
    @TableField(value = "name")
    private String name;

    @TableField(value = "sort")
    private Integer sort;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "createTime")
    private Date createTime;

    @TableField(value = "updateTime")
    private Date updateTime;

    @TableField(value = "remark")
    private String remark;

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

    @Override
    protected Serializable pkVal() {
        return id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
