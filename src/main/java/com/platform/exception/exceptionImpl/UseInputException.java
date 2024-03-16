package com.platform.exception.exceptionImpl;

import lombok.Data;

/**
 * @author MungDong
 * @create 2024-03-16-14:39
 */
@Data
public class UseInputException extends RuntimeException {

    public String message;

    public UseInputException(String message){
        this.message= message;
    }

}
