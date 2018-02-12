package com.springboot.starter.entity.authority;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author baker
 */
@TableName(value = "t_auth_role")
public class SysRole extends Model<SysRole>{

    private static final long serialVersionUID = 6714786296451717984L;

    @TableId
    private Long id;

    @TableField(value = "name")
    private String name;

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
}
