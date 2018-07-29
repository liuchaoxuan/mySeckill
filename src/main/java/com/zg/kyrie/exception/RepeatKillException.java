package com.zg.kyrie.exception;

/**
 * @Auther: kyrie
 * @Date: 2018/7/28 22:16
 */
public class RepeatKillException extends RuntimeException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
