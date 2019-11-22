package com.kisin.gen.common.exception;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-07-25 21:04
 * @Description:
 */
public class KisinException extends Exception {

    private String errorCode;

    public KisinException(String message) {
        super(message);
    }

    public KisinException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public KisinException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
