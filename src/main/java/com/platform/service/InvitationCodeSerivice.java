package com.platform.service;

import com.platform.domain.Result;
import com.platform.domain.vo.InvitationCodeVO;

import java.util.List;

public interface InvitationCodeSerivice {
    Result insertInvitationCode();

    int discardInvitationCode(InvitationCodeVO invitationCodeVO);

    InvitationCodeVO findLastCode();

    InvitationCodeVO findCode(String code);

    List<InvitationCodeVO> listCode();

}
