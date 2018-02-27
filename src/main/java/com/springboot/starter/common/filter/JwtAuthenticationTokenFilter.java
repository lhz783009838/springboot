package com.springboot.starter.common.filter;

import com.springboot.starter.common.properties.AuthorizationProperties;
import com.springboot.starter.common.utils.EntryTimeContextHolder;
import com.springboot.starter.common.utils.JwtTokenUtil;
import com.springboot.starter.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    @Autowired
    private AuthorizationProperties authorizationProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (null == EntryTimeContextHolder.getEntryTime()) {
            EntryTimeContextHolder.setEntryTime(System.currentTimeMillis());
        }
        String tokenHead = authorizationProperties.getJwt().getTokenHead();
        String header = req.getHeader(authorizationProperties.getJwt().getHeader());
        if (StringUtils.isNotEmpty(header) && header.startsWith(tokenHead)) {
            final String authToken = header.substring(tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (StringUtils.isNotEmpty(username)) {
                UserDetails userDetails = this.sysUserService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }else {
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }
        filterChain.doFilter(req, rsp);
    }
}
