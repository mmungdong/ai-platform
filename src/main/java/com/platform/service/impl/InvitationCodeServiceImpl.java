package com.platform.service.impl;

import com.platform.domain.InvitationCode;
import com.platform.domain.InviteCodeUtil;
import com.platform.domain.Result;
import com.platform.domain.User;
import com.platform.mapper.InvitationCodeMapper;
import com.platform.mapper.UserMapper;
import com.platform.service.InvitationCodeSerivice;
import com.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class InvitationCodeServiceImpl implements InvitationCodeSerivice {

    @Autowired
    private InvitationCodeMapper invitationCodeMapper;
    @Override
    public Result insertInvitationCode() {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);

        try {
            InvitationCode lastCode = findLastCode();
            Integer id = lastCode.getId();
            long startLongId = id.longValue();
            for (int i = 0; i < 10; i++) {
                String gen = InviteCodeUtil.gen(++startLongId);
                InvitationCode insertInvitationCode = new InvitationCode(gen, "NORMAL");
                invitationCodeMapper.insertCode(insertInvitationCode);
            }
            result.setMsg("生成邀请码成功");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateInvitationCodeStatus(InvitationCode invitationCode) {
        return invitationCodeMapper.updateCodeStatus(invitationCode);
    }

    @Override
    public InvitationCode findLastCode() {
        return invitationCodeMapper.findLastCode();
    }

    @Override
    public InvitationCode findCode(String code) {
        return invitationCodeMapper.findCode(code);
    }

    @Override
    public List<InvitationCode> listCode() {
        return invitationCodeMapper.listCode();
    }

}
