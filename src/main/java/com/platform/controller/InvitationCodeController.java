package com.platform.controller;

import com.platform.domain.InviteCodeUtil;
import com.platform.domain.Result;
import com.platform.domain.User;
import com.platform.service.impl.InvitationCodeServiceImpl;
import com.platform.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class InvitationCodeController {

    @Autowired
    private InvitationCodeServiceImpl invitationCodeService;

    @GetMapping(value = "/genCode")
    public Result genCode(){
        //批量生成，每次生成10个
        return invitationCodeService.insertInvitationCode();
    }

}

