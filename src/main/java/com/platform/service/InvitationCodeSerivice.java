package com.platform.service;

import com.platform.domain.InvitationCode;
import com.platform.domain.Result;

import java.util.List;

public interface InvitationCodeSerivice {
    Result insertInvitationCode();

    int updateInvitationCodeStatus(InvitationCode invitationCode);

    InvitationCode findLastCode();

    InvitationCode findCode(String code);

    List<InvitationCode> listCode();

}
