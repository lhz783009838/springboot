package com.springboot.starter.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.springboot.starter.common.properties.DataSourceProperties;
import com.springboot.starter.common.properties.DruidProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;
import java.util.Properties;

/**
 * @author baker
 */
@Configuration
public class DruidDbConfig {

    private static final Logger logger = LoggerFactory.getLogger(DruidDbConfig.class);

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name = "dataSource")
    @Primary
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        DruidProperties druidProperties = dataSourceProperties.getDruid();
        dataSource.setDriverClassName(druidProperties.getDriverClassName());
        dataSource.setUrl(druidProperties.getUrl());
        dataSource.setUsername(druidProperties.getUsername());
        dataSource.setPassword(druidProperties.getPassword());
        dataSource.setInitialSize(druidProperties.getInitialSize());
        dataSource.setMinIdle(druidProperties.getMinIdle());
        dataSource.setMaxActive(druidProperties.getMaxActive());
        dataSource.setMaxWait(druidProperties.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        dataSource.setDbType(druidProperties.getDbType());
        String validationQuery = druidProperties.getValidationQuery();
        if (StringUtils.isNotEmpty(validationQuery)) {
            dataSource.setValidationQuery(validationQuery);
        }
        dataSource.setTestWhileIdle(druidProperties.getTestWhileIdle());
        dataSource.setTestOnBorrow(druidProperties.getTestOnBorrow());
        dataSource.setTestOnReturn(druidProperties.getTestOnReturn());
        if (druidProperties.getPoolPreparedStatements()) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
        }
        //这是最关键的,否则SQL监控无法生效
        try {
            dataSource.setFilters(druidProperties.getFilters());
        } catch (SQLException e) {
            logger.error("连接池初始化失败!");
            e.printStackTrace();
        }
        String connectionPropertiesStr = druidProperties.getConnectionProperties();
        if (StringUtils.isNotEmpty(connectionPropertiesStr)) {
            Properties connectProperties = new Properties();
            String[] propertiesList = connectionPropertiesStr.split(";");
            for (String propertiesTmp : propertiesList) {
                String[] obj = propertiesTmp.split("=");
                String key = obj[0];
                String value = obj[1];
                connectProperties.put(key, value);
            }
            dataSource.setConnectProperties(connectProperties);
        }
        dataSource.setUseGlobalDataSourceStat(druidProperties.getUseGlobalDataSourceStat());
        return dataSource;
    }
}
