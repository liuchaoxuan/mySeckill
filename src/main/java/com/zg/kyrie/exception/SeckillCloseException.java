package com.zg.kyrie.exception;

/**
 * @Auther: kyrie
 * @Date: 2018/7/28 22:18
 */
public class SeckillCloseException extends RuntimeException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
