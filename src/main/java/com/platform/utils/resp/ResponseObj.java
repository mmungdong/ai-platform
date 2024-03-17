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
        return new ResponseObj(CodeConstant.SUCCESS_CODE.getCode(), "success", null);
    }

    public static ResponseObj success(String message, Object data) {
        return new ResponseObj(CodeConstant.SUCCESS_CODE.getCode(), message, data);
    }

    public static ResponseObj success(Object data) {
        return new ResponseObj(CodeConstant.SUCCESS_CODE.getCode(), "success", data);
    }

    public static ResponseObj badCredentialsError(String message) {
        return new ResponseObj(CodeConstant.BAD_CREDENTIALS_ERROR.getCode(), message);
    }

    public static ResponseObj internalServerError(String message) {
        return new ResponseObj(CodeConstant.INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public static ResponseObj fail(String message) {
        return new ResponseObj(CodeConstant.INFO_ERROR.getCode(), message);
    }

    public static ResponseObj fail(Object data) {
        return new ResponseObj(CodeConstant.INFO_ERROR.getCode(), "Fail", data);
    }

}