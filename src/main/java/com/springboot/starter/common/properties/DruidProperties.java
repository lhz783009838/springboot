package com.springboot.starter.common.properties;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Druid连接池配置
 *
 * @author linhuanzhen
 */
public class DruidProperties {

    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String dbType;
    /**
     * 下面为连接池的补充设置，应用到上面所有数据源中
     * 初始化大小，最小，最大
     */
    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    /**
     * 配置获取连接等待超时的时间
     */
    private Long maxWait;

    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private Long timeBetweenEvictionRunsMillis;
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小 默认为true 20
     */
    private boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    /**
     * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙 如果不配置,走默认值
     */
    private String filters;
    /**
     * 通过connectProperties属性来打开mergeSql功能；慢SQL记录  默认为打开
     */
    private String connectionProperties;
    /**
     * 合并多个DruidDataSource的监控数据 默认为true
     */
    private boolean useGlobalDataSourceStat;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Long maxWait) {
        this.maxWait = maxWait;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean getPoolPreparedStatements() {
        if (!ObjectUtils.allNotNull(poolPreparedStatements)) {
            poolPreparedStatements = true;
        }
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public Integer getMaxPoolPreparedStatementPerConnectionSize() {
        if (!ObjectUtils.allNotNull(maxPoolPreparedStatementPerConnectionSize)) {
            maxPoolPreparedStatementPerConnectionSize = 20;
        }
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        if (StringUtils.isBlank(filters)) {
            filters = "stat,wall,log4j,config";
        }
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getConnectionProperties() {
        if (StringUtils.isBlank(connectionProperties)) {
            connectionProperties = "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000";
        }
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public boolean getUseGlobalDataSourceStat() {
        if (!ObjectUtils.allNotNull(useGlobalDataSourceStat)) {
            useGlobalDataSourceStat = true;
        }
        return useGlobalDataSourceStat;
    }

    public void setUseGlobalDataSourceStat(boolean useGlobalDataSourceStat) {
        this.useGlobalDataSourceStat = useGlobalDataSourceStat;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
