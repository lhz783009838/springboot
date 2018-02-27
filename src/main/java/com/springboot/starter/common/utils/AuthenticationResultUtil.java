package com.springboot.starter.common.utils;

import com.springboot.starter.common.constants.DefaultExceptionMsg;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

/**
 * @author baker
 */
public final class AuthenticationResultUtil {

    private AuthenticationResultUtil() {
    }

    public static String genericForbiddenResult(Exception e) {
        String result = null;
        if(e instanceof BadCredentialsException){
            result = DefaultExceptionMsg.BAD_CREDENTIALS_EXCEPTION;
        } else if (e instanceof DisabledException) {
            result = DefaultExceptionMsg.ACCOUNT_DISABLED_EXCEPTION;
        }
        return result;
    }
}
