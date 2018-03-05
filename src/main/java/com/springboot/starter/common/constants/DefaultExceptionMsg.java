package com.springboot.starter.common.constants;

/**
 * @author baker
 * 默认异常信息提示
 */
public interface DefaultExceptionMsg {

    String SYSTEM_ERROR_EXCEPTION = "系统异常";

    String NO_HANDLER_FOUND_EXCEPTION = "无效的请求地址，请确认请求url是否正确";

    String METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "请求参数错误";

    String BIND_EXCEPTION = "请求参数无法通过校验";

    String HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION = "该请求不支持【%s】方法";

    String MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION = "请求缺少【%s】参数";

    String FORBIDDEN = "拒绝未登录访问";

    String UNAUTHORIZED = "无访问权限";

    String BAD_CREDENTIALS_EXCEPTION = "账号或密码错误";

    String ACCOUNT_DISABLED_EXCEPTION = "账号被锁定或禁用";

    String HTTP_MESSAGE_NOT_READABLE_EXCEPTION = "读取JSON参数失败，请确认格式是否正确";
}
