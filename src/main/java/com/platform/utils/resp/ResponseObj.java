package com.platform.utils.resp;

import com.platform.constant.CodeConstant;
import lombok.Data;

/**
 * @author MungDong
 * @create 2024-03-16-14:40
 */
@Data
public class ResponseObj<T> {

    private String code;

    private String message;

    private T data;

    public ResponseObj(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseObj(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseObj success() {
        return new ResponseObj(CodeConstant.SUCCESS_CODE.getCode(), "Success", null);
    }

    public static ResponseObj success(String message, Object data) {
        return new ResponseObj(CodeConstant.SUCCESS_CODE.getCode(), message, data);
    }

    public static ResponseObj success(Object data) {
        return new ResponseObj(CodeConstant.SUCCESS_CODE.getCode(), "Success", data);
    }

    public static ResponseObj fail(String message) {
        return new ResponseObj(CodeConstant.INFO_ERROR.getCode(), message);
    }

    public static ResponseObj fail(Object data) {
        return new ResponseObj(CodeConstant.INFO_ERROR.getCode(), "Fail", data);
    }

    public static ResponseObj error(String message) {
        return new ResponseObj(CodeConstant.ERROR_CODE.getCode(), message);
    }

    public static ResponseObj error(Object data) {
        return new ResponseObj(CodeConstant.ERROR_CODE.getCode(), "error", data);
    }

}