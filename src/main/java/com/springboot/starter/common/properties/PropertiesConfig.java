package com.springboot.starter.common.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置读取器
 * @author linhuanzhen
 */
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class,AuthorizationProperties.class})
public class PropertiesConfig {
}
