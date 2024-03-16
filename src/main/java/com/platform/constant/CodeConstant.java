package com.platform.constant;

/**
 * @author MungDong
 * @create 2024-03-16-14:41
 */
public enum CodeConstant {

    ERROR_CODE("500"),//系统逻辑错误

    INFO_ERROR("1000"),

    SUCCESS_CODE("200");//成功

    private final String code;

    CodeConstant(String code)
    {
        this.code=code;
    }

    public String getCode() {
        return code;
    }
}