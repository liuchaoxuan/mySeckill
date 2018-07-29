package com.zg.kyrie.exception;

/**
 * @Auther: kyrie
 * @Date: 2018/7/28 22:15
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
