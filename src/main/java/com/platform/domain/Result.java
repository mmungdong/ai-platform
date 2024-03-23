package com.platform.domain;

import javax.servlet.http.Cookie;

/**
 * 向前端返回信息封装
 * @param <T> 可变类型
 */
public class Result<T> {

    /**
     * 数据是否正常请求
     */
    private boolean success;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 具体返回的数据
     */
    private T data;

    private Cookie cookie;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public void setCookie(String name, String value, int maxAge) {
        this.cookie = new Cookie(name, value);
        this.cookie.setMaxAge(maxAge);
    }
}
