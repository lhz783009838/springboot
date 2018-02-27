package com.springboot.starter.common.handle;

import com.springboot.starter.common.response.DataResult;
import com.springboot.starter.common.utils.EntryTimeContextHolder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * @author big god
 */
public class RestWebLogInterceptorHandler implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (logger.isInfoEnabled()) {
            long beginTime = System.currentTimeMillis();
            EntryTimeContextHolder.setEntryTime(beginTime);
            logger.info("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime), request.getRequestURI());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            logger.info("viewName: " + modelAndView.getViewName());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        // 打印JVM信息。
        if (logger.isInfoEnabled()) {
            //得到线程绑定的局部变量（开始时间）
            Long beginTime = EntryTimeContextHolder.getEntryTime();
            //2、结束时间
            long endTime = System.currentTimeMillis();
            logger.info("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateFormatUtils.format(endTime-beginTime,"ss"),
                    request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
            //删除线程变量中的数据，防止内存泄漏
            EntryTimeContextHolder.clear();
            if (handler instanceof DataResult) {
                ((DataResult) handler).setTime(endTime - beginTime);
            }
        }
    }
}
