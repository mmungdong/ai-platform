package com.platform.controller;

import com.platform.domain.InvitationCode;
import com.platform.domain.Result;
import com.platform.domain.User;
import com.platform.service.InvitationCodeSerivice;
import com.platform.service.impl.InvitationCodeServiceImpl;
import com.platform.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private InvitationCodeServiceImpl invitationCodeService;

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/regist")
    public Result regist(@RequestBody User user){

        InvitationCode code = invitationCodeService.findCode(user.getCode());
        return userService.regist(user);
    }

    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }
}

