package com.springboot.starter.common.constants;

/**
 * @author baker
 */
public interface ResultCodeConstants {

    String RESULT_CODE_SUCCESS = "0000";

    String RESULT_CODE_FAIL_BI_ERROR = "0010";

    String RESULT_CODE_FAIL_SYS_ERROR = "0500";

    String RESULT_CODE_FAIL_SYS_FORBIDDEN = "0403";

    String RESULT_CODE_FAIL_SYS_UNAUTHORIZED = "0401";

    String RESULT_MSG_SUCCESS = "成功";

    String RESULT_MSG_FAIL_BI_ERROR = "失败";

    String RESULT_MSG_FAIL_BI_SYS_ERROR = "系统异常";
}
