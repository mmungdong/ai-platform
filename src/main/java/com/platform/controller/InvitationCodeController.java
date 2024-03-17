package com.platform.controller;

import com.platform.domain.InvitationCode;
import com.platform.domain.InviteCodeUtil;
import com.platform.domain.Result;
import com.platform.domain.User;
import com.platform.service.impl.InvitationCodeServiceImpl;
import com.platform.service.impl.UserServiceImpl;
import com.platform.utils.resp.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/code")
public class InvitationCodeController {

    @Autowired
    private InvitationCodeServiceImpl invitationCodeService;

    // getCode: list all code
    @GetMapping
    public ResponseObj getCode(@RequestParam(value = "code", required = false) String code){
        if (StringUtils.hasText(code)) {
            // select one
            InvitationCode invitationCode = invitationCodeService.findCode(code);
            if (Objects.isNull(invitationCode)) {
                return ResponseObj.success("该邀请码不存在!");
            }
            return ResponseObj.success("查询成功", invitationCode);
        }
        // select all
        List<InvitationCode> invitationCodes = invitationCodeService.listCode();
        //批量生成，每次生成10个
        return ResponseObj.success("查询成功", invitationCodes);
    }

    // genCode: generate Invitation Code
    @GetMapping(value = "/gen")
    public Result genCode(){
        return invitationCodeService.insertInvitationCode();
    }

}

