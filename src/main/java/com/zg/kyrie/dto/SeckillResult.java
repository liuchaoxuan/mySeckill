package com.zg.kyrie.dto;

/**
 * @Auther: kyrie
 * @Date: 2018/7/28 12:32
 * 讲所有ajax的请求，结果封装成json
 */
public class SeckillResult<T> {
    public boolean success;
    public T data;
    public String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
