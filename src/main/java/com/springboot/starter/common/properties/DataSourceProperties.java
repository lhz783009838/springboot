package com.springboot.starter.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据库配置读取
 * @author baker
 */
@ConfigurationProperties(prefix = "spring.mysql")
public class DataSourceProperties {

    private DruidProperties druid = new DruidProperties();

    private MybatisProperties mybatis = new MybatisProperties();

    public DruidProperties getDruid() {
        return druid;
    }

    public void setDruid(DruidProperties druid) {
        this.druid = druid;
    }

    public MybatisProperties getMybatis() {
        return mybatis;
    }

    public void setMybatis(MybatisProperties mybatis) {
        this.mybatis = mybatis;
    }
}
