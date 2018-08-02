package com.zg.kyrie.dto;

/**
 * @Auther: kyrie
 * @Date: 2018/8/2 22:51
 */
public class SearchResult <T>{
    private T data;

    public SearchResult(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "data=" + data +
                '}';
    }
}
