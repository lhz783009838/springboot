package com.springboot.starter.common.handle;

import com.springboot.starter.common.constants.DefaultExceptionMsg;
import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.common.utils.AuthenticationResultUtil;
import com.springboot.starter.common.utils.JsonResultBuildUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author baker
 * 未登录及登录认证错误处理器
 */
public class RestAuthenticationEntryPointHandler implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest req, HttpServletResponse rsp, AuthenticationException e) throws IOException, ServletException {
        String result = AuthenticationResultUtil.genericForbiddenResult(e);
        JsonResultBuildUtil.responseResult(rsp, DataResult.forbidden(result));
    }
}
