package com.springboot.starter.common.config;

import com.springboot.starter.common.filter.JwtAuthenticationTokenFilter;
import com.springboot.starter.common.handle.RestAuthenticationAccessDeniedHandler;
import com.springboot.starter.common.handle.RestAuthenticationEntryPointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author linhuanzhen
 * 安全配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new RestAuthenticationAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new RestAuthenticationEntryPointHandler();
    }

    /**
     * 定义用户验证需要的配置
     *
     * @param auth 用户校验管理器
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 不自定义authenticationProvider时默认使用DaoAuthenticationProvider,需要定义该校验器的UserDetailService及密码解析器
        // 注： 如果不定义密码解析器，DaoAuthentication默认使用的是PlaintextPasswordEncoder(原因看代码),导致密码比对失败
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 自定义 401 异常
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                //　自定义 403 异常
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                // jwt不需要 csrf
                .csrf().disable()
                // 不需要缓存
                .sessionManagement().disable()
                // 配置放行url
                .authorizeRequests()
                // ajax options放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 静态资源放行
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // auth请求相关放行
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/user/**").permitAll()
                // 其它请求全部拦截
                .anyRequest().authenticated();
        // 添加token校验拦截器
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 禁用请求头缓存
        httpSecurity.headers().cacheControl();
    }
}
