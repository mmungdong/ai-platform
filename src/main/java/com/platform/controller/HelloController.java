package com.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MungDong
 * @create 2024-03-14-1:27
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String hello(){
        return "login.html";
    }

}