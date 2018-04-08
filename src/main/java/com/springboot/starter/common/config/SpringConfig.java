package com.springboot.starter.common.config;

import com.springboot.starter.common.handle.DefaultRolesPrefixPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author linhuanzhen
 */
@Configuration
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public DefaultRolesPrefixPostProcessor defaultRolesPrefixPostProcessor(){
        return new DefaultRolesPrefixPostProcessor();
    }
}
