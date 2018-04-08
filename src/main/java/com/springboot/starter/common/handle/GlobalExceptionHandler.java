package com.springboot.starter.common.handle;

import com.springboot.starter.common.constants.DefaultExceptionMsg;
import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.common.utils.ExceptionResultUtil;
import com.springboot.starter.common.utils.JsonResultBuildUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
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
 * @author linhuanzhen
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
        logger.info("[0]", ex);
        String result = "";
        if (ex instanceof NoHandlerFoundException) {
            JsonResultBuildUtil.responseResult(rsp, DataResult.fail(DefaultExceptionMsg.NO_HANDLER_FOUND_EXCEPTION));
        } else if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            for (FieldError error : bindException.getBindingResult().getFieldErrors()) {
                result += "【 " + error.getField() + " : " + error.getDefaultMessage() + " 】";
            }
            JsonResultBuildUtil.responseResult(rsp, DataResult.fail(DefaultExceptionMsg.BIND_EXCEPTION, result));
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) ex;
            for (FieldError error : argumentNotValidException.getBindingResult().getFieldErrors()) {
                result += "【 " + error.getField() + " : " + error.getDefaultMessage() + " 】";
            }
            JsonResultBuildUtil.responseResult(rsp, DataResult.fail(DefaultExceptionMsg.METHOD_ARGUMENT_NOT_VALID_EXCEPTION, result));
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException methodNotSupportedException = (HttpRequestMethodNotSupportedException) ex;
            result = methodNotSupportedException.getMessage();
            JsonResultBuildUtil.responseResult(rsp, DataResult.fail(String.format(DefaultExceptionMsg
                    .HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, methodNotSupportedException.getMethod()), result));
        } else if (ex instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException requestParameterException = (MissingServletRequestParameterException) ex;
            result = requestParameterException.getMessage();
            JsonResultBuildUtil.responseResult(rsp, DataResult.fail(String.format(DefaultExceptionMsg
                    .MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION, requestParameterException.getParameterName()), result));
        } else if (ex instanceof AuthenticationException) {
            result = ExceptionResultUtil.genericForbiddenResult(ex);
            JsonResultBuildUtil.responseResult(rsp, DataResult.forbidden(result));
        } else if (ex instanceof HttpMessageNotReadableException) {
            result = ExceptionUtils.getMessage(ex);
            JsonResultBuildUtil.responseResult(rsp, DataResult.fail(DefaultExceptionMsg.HTTP_MESSAGE_NOT_READABLE_EXCEPTION, result));
        } else if (ex instanceof AccessDeniedException) {
            JsonResultBuildUtil.responseResult(rsp, DataResult.unauthorized(DefaultExceptionMsg.UNAUTHORIZED));
        } else {
            result = ExceptionUtils.getMessage(ex);
            JsonResultBuildUtil.responseResult(rsp, DataResult.fail(DefaultExceptionMsg.SYSTEM_ERROR_EXCEPTION, result));
        }
    }
}
