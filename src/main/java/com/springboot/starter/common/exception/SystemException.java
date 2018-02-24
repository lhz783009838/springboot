package com.springboot.starter.common.exception;

/**
 * @author baker
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 7129758467109867145L;

    public SystemException(String message) {
        super(message);
    }
}
