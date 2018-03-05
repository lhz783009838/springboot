package com.springboot.starter.common.handle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.security.access.annotation.Jsr250MethodSecurityMetadataSource;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

/**
 * @author baker
 * 自定义角色前缀配置
 */
public class DefaultRolesPrefixPostProcessor implements BeanPostProcessor, PriorityOrdered {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // remove this if you are not using JSR-250
        if(bean instanceof Jsr250MethodSecurityMetadataSource) {
            ((Jsr250MethodSecurityMetadataSource) bean).setDefaultRolePrefix("");
        }
        if(bean instanceof DefaultMethodSecurityExpressionHandler) {
            ((DefaultMethodSecurityExpressionHandler) bean).setDefaultRolePrefix("");
        }
        if(bean instanceof DefaultWebSecurityExpressionHandler) {
            ((DefaultWebSecurityExpressionHandler) bean).setDefaultRolePrefix("");
        }
        if(bean instanceof SecurityContextHolderAwareRequestFilter) {
            ((SecurityContextHolderAwareRequestFilter)bean).setRolePrefix("");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }
}
