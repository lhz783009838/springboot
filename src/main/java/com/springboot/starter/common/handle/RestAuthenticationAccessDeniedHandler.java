package com.springboot.starter.common.handle;

import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.common.utils.JsonResultBuildUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author linhuanzhen
 */
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse rsp, AccessDeniedException e) throws IOException, ServletException {
        JsonResultBuildUtil.responseResult(rsp, DataResult.unauthorized());
    }
}
