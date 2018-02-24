package com.springboot.starter.common.handle;

import com.alibaba.fastjson.JSONObject;
import com.springboot.starter.common.constants.DefaultExceptionMsg;
import com.springboot.starter.common.response.DataResult;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author baker
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常处理
     *
     * @param req req
     * @param rsp rsp
     * @param ex  ex
     *            404异常系统默认不抛出异常，直接跳入/error,具体实现在DispatcherServlet ->throwExceptionIfNoHandlerFound属性
     *            需要在application.yml中配置
     */
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletRequest req, HttpServletResponse rsp, Exception ex) {
        logger.error("====出现异常====", ex);
        String result = "";
        if (ex instanceof NoHandlerFoundException) {
            responseResult(rsp, DataResult.fail(DefaultExceptionMsg.NO_HANDLER_FOUND_EXCEPTION));
        } else if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            for (FieldError error : bindException.getBindingResult().getFieldErrors()) {
                result += "【 " + error.getField() + " : " + error.getDefaultMessage() + " 】";
            }
            responseResult(rsp, DataResult.fail(DefaultExceptionMsg.BIND_EXCEPTION, result));
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) ex;
            for (FieldError error : argumentNotValidException.getBindingResult().getFieldErrors()) {
                result += "【 " + error.getField() + " : " + error.getDefaultMessage() + " 】";
            }
            responseResult(rsp, DataResult.fail(DefaultExceptionMsg.METHOD_ARGUMENT_NOT_VALID_EXCEPTION, result));
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException methodNotSupportedException = (HttpRequestMethodNotSupportedException) ex;
            result = methodNotSupportedException.getMessage();
            responseResult(rsp, DataResult.fail(String.format(DefaultExceptionMsg
                    .HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, methodNotSupportedException.getMethod()), result));
        } else if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException requestParameterException = (MissingServletRequestParameterException) ex;
            result = requestParameterException.getMessage();
            responseResult(rsp, DataResult.fail(String.format(DefaultExceptionMsg
                    .MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION, requestParameterException.getParameterName()), result));
        } else if (ex instanceof AuthenticationException) {
            result = ExceptionUtils.getMessage(ex);
            responseResult(rsp, DataResult.forbidden(result));
        } else {
            result = ExceptionUtils.getMessage(ex);
            responseResult(rsp, DataResult.fail(DefaultExceptionMsg.SYSTEM_ERROR_EXCEPTION, result));
        }
    }

    private void responseResult(HttpServletResponse rsp, DataResult res) {
        try {
            rsp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            rsp.setCharacterEncoding("utf-8");
            rsp.getWriter().write(JSONObject.toJSONString(res));
        } catch (Exception e) {
            logger.error("响应数据异常，e={0}", e);
        }

    }
}
