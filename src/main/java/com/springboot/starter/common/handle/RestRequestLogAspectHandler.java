package com.springboot.starter.common.handle;

import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.common.utils.EntryTimeContextHolder;
import com.springboot.starter.common.utils.WebUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author linhuanzhen
 * 控制器切面，用于记录请求基本信息及通过控制器响应时的时间戳
 */
@Component
@Aspect
public class RestRequestLogAspectHandler {

    private static final Logger logger = Logger.getLogger(RestRequestLogAspectHandler.class);

    @Pointcut("execution(public * com.springboot.starter.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        EntryTimeContextHolder.setEntryTime(System.currentTimeMillis());
        logger.info("URL: " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD: " + request.getMethod());
        logger.info("IP: " + WebUtil.getIpAddress(request));
        logger.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws InterruptedException {
        logger.info("RESPONSE: " + ret);
        long handleTime = System.currentTimeMillis() - EntryTimeContextHolder.getEntryTime();
        logger.info("TIME: " + handleTime);
        if (ret instanceof DataResult) {
            DataResult result = (DataResult) ret;
            result.setTime(handleTime);
        }
        EntryTimeContextHolder.clear();
    }
}
