package com.platform.exception;

import com.platform.exception.exceptionImpl.UseInputException;
import com.platform.utils.resp.ResponseObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author MungDong
 * @create 2024-03-16-14:39
 */
@ControllerAdvice //统一异常处理
public class GlobalExceptionHandler {

    private final static Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Resource
    private HttpServletRequest httpServletRequest;

    @ExceptionHandler({UseInputException.class})//处理异常类
    @ResponseBody
    public ResponseObj<String> useInputExceptionHandler(Exception e){
        logger.info("请求地址:"+httpServletRequest.getRequestURI()+"-异常；错误信息:");
        e.printStackTrace();
        return ResponseObj.fail("用户携带/传入数据异常");
    }

    @ExceptionHandler({Exception.class})//处理异常类
    @ResponseBody
    public ResponseObj<String> exceptionHandler(Exception e){
        logger.info("请求地址:"+httpServletRequest.getRequestURI()+"-异常；错误信息:");
        e.printStackTrace();
        return ResponseObj.fail("系统异常");
    }

}
