package com.platform.constant;

/**
 * @author MungDong
 * @create 2024-03-16-14:41
 */
public enum CodeConstant {

    BAD_CREDENTIALS_ERROR("401"),

    INFO_ERROR("400"),

    SUCCESS_CODE("200"),

    INTERNAL_SERVER_ERROR("500");

    private final String code;

    CodeConstant(String code)
    {
        this.code=code;
    }

    public String getCode() {
        return code;
    }
}