package com.springboot.starter.common.handle;

import com.alibaba.fastjson.JSONObject;
import com.springboot.starter.common.constants.DefaultExceptionMsg;
import com.springboot.starter.common.response.DataResult;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author baker
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

}
