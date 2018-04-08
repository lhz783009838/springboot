package com.springboot.starter.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.springboot.starter.common.response.DataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;

/**
 * @author linhuanzhen
 */
public final class JsonResultBuildUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonResultBuildUtil.class);

    private JsonResultBuildUtil() {
    }

    public static void responseResult(HttpServletResponse rsp, DataResult res) {
        try {
            rsp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            rsp.setCharacterEncoding("utf-8");
            long handleTime = System.currentTimeMillis() - EntryTimeContextHolder.getEntryTime();
            res.setTime(handleTime);
            rsp.getWriter().write(JSONObject.toJSONString(res));
        } catch (Exception e) {
            logger.error("响应数据异常，e={0}", e);
        }

    }

}
