package com.platform.controller;

import com.platform.domain.Result;
import com.platform.domain.vo.LoginUserVO;
import com.platform.domain.vo.RegistUserVO;
import com.platform.service.impl.InvitationCodeServiceImpl;
import com.platform.service.impl.UserServiceImpl;
import com.platform.utils.resp.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result regist(@RequestBody RegistUserVO user){
        return userService.regist(user);
    }

    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/login")
    public ResponseObj login(@RequestBody LoginUserVO user){
        return userService.login(user);
    }

    @GetMapping(value = "/logout")
    public ResponseObj logout(@RequestBody LoginUserVO user){
        return userService.logout(user);
    }
}

