package com.kisin.gen.common.exception;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-07-25 21:12
 * @Description:
 */
public class KisinRuntimeException extends RuntimeException {
    private String errorCode;

    public KisinRuntimeException(String message) {
        super(message);
    }

    public KisinRuntimeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public KisinRuntimeException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
