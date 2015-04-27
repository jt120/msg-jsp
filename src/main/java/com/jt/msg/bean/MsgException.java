package com.jt.msg.bean;

/**
 * since 2015/4/27.
 */
public class MsgException extends RuntimeException {
    public MsgException() {
        super();
    }

    public MsgException(String message) {
        super(message);
    }

    public MsgException(String message, Throwable cause) {
        super(message, cause);
    }
}
