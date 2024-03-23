package com.platform.service.impl;

import com.platform.utils.InviteCodeUtil;
import com.platform.domain.Result;
import com.platform.domain.entity.InvitationCodeDO;
import com.platform.domain.vo.InvitationCodeVO;
import com.platform.mapper.InvitationCodeMapper;
import com.platform.service.InvitationCodeSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        result.setData(null);

        try {
            InvitationCodeVO lastCode = findLastCode();
            Long id = lastCode.getId();
            long startLongId = id.longValue();
            for (int i = 0; i < 10; i++) {
                String gen = InviteCodeUtil.gen(++startLongId);
                InvitationCodeDO invitationCodeDo = new InvitationCodeDO();
                invitationCodeDo.setCode(gen);
                invitationCodeDo.setStatus("NORMAL");
                invitationCodeDo.setCreateTime(new Date(System.currentTimeMillis()));
                invitationCodeMapper.insertCode(invitationCodeDo);
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
    public int discardInvitationCode(InvitationCodeVO invitationCode) {
        return invitationCodeMapper.discardInvitationCode(invitationCode.getCode());
    }

    @Override
    public InvitationCodeVO findLastCode() {
        return invitationCodeMapper.findLastCode();
    }

    @Override
    public InvitationCodeVO findCode(String code) {
        InvitationCodeDO invitationCodeDO = invitationCodeMapper.findCode(code);

        return new InvitationCodeVO(invitationCodeDO.getId(), invitationCodeDO.getCode(), invitationCodeDO.getStatus());
    }

    @Override
    public List<InvitationCodeVO> listCode() {
        return invitationCodeMapper.listCode();
    }

}
