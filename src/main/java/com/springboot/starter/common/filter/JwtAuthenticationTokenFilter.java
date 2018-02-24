package com.springboot.starter.common.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author baker
 * token校验拦截器
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("拦截");
        filterChain.doFilter(req, rsp);
    }
}
