package com.springboot.starter.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisMapperRefresh;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.springboot.starter.common.properties.DataSourceProperties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * mybatis-plus 配置类
 *
 * @author baker
 */
@Configuration
@MapperScan(value = "com.springboot.starter.dao")
public class MybatisPlusConfig {

    @Autowired
    private DataSourceProperties properties;

    @Bean(name = "mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DruidDataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.springboot.starter.entity");
        sessionFactoryBean.setTypeEnumsPackage("com.springboot.starter");
        sessionFactoryBean.setMapperLocations(resources(properties.getMybatis().getMapperLocations()));
        sessionFactoryBean.setConfiguration(mybatisConfiguration());
        sessionFactoryBean.setGlobalConfig(globalConfiguration());
        sessionFactoryBean.setPlugins(interceptors());
        return sessionFactoryBean.getObject();
    }

    @Bean
    public MybatisConfiguration mybatisConfiguration() {
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        return mybatisConfiguration;
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setDbType(DBType.MYSQL.getDb());
        globalConfiguration.setLogicDeleteValue("-1");
        globalConfiguration.setLogicNotDeleteValue("1");
        globalConfiguration.setSqlInjector(logicSqlInjector());
        globalConfiguration.setIdType(IdType.ID_WORKER.getKey());
        return globalConfiguration;
    }

    @Bean
    public LogicSqlInjector logicSqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    public MybatisMapperRefresh mapperRefresh(SqlSessionFactory sessionFactory) throws IOException {
        String path = properties.getMybatis().getMapperLocations();
        return new MybatisMapperRefresh(resources(path),sessionFactory,true);
    }

    private Interceptor[] interceptors(){
        return new Interceptor[]{new PaginationInterceptor(),new PerformanceInterceptor(),new OptimisticLockerInterceptor()};
    }

    private Resource[] resources(String path) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            return resolver.getResources(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
