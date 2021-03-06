package com.springboot.starter.common.response;

import com.springboot.starter.common.constants.DefaultExceptionMsg;
import com.springboot.starter.common.constants.ResultCodeConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * @author linhuanzhen
 */
public class DataResult {

    private String status;

    private String msg;

    private Long time = 0L;

    private Object result;

    private String trace;

    public DataResult(String status, String msg, Long time, Object result, String trace) {
        this.status = status;
        this.msg = msg;
        this.time = time;
        this.result = result;
        this.trace = trace;
    }

    public static DataResult success() {
        return new DataResult(ResultCodeConstants.RESULT_CODE_SUCCESS, ResultCodeConstants.RESULT_MSG_SUCCESS,
                0L, "", "");
    }

    public static DataResult success(String msg) {
        return new DataResult(ResultCodeConstants.RESULT_CODE_SUCCESS, msg,
                0L, "", "");
    }

    public static DataResult success(String msg, Object result) {
        return new DataResult(ResultCodeConstants.RESULT_CODE_SUCCESS, msg, 0L, result, "");
    }

    public static DataResult fail() {
        return new DataResult(ResultCodeConstants.RESULT_CODE_FAIL_BI_ERROR, ResultCodeConstants.RESULT_MSG_FAIL_BI_ERROR,
                0L, "", "");
    }

    public static DataResult fail(String msg) {
        return new DataResult(ResultCodeConstants.RESULT_CODE_FAIL_BI_ERROR, msg,
                0L, "", "");
    }

    public static DataResult fail(String msg, String trace) {
        return new DataResult(ResultCodeConstants.RESULT_CODE_FAIL_BI_ERROR, msg,
                0L, "", trace);
    }

    public static DataResult forbidden() {
        return new DataResult(ResultCodeConstants.RESULT_CODE_FAIL_SYS_FORBIDDEN, DefaultExceptionMsg.FORBIDDEN,
                0L, "", "");
    }

    public static DataResult forbidden(String msg) {
        msg = StringUtils.isEmpty(msg) ? DefaultExceptionMsg.FORBIDDEN : msg;
        return new DataResult(ResultCodeConstants.RESULT_CODE_FAIL_SYS_FORBIDDEN, msg,
                0L, "", "");
    }

    public static DataResult unauthorized() {
        return new DataResult(ResultCodeConstants.RESULT_CODE_FAIL_SYS_UNAUTHORIZED, DefaultExceptionMsg.UNAUTHORIZED,
                0L, "", "");
    }

    public static DataResult unauthorized(String msg) {
        return new DataResult(ResultCodeConstants.RESULT_CODE_FAIL_SYS_UNAUTHORIZED, msg,
                0L, "", "");
    }

    public DataResult() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
