package com.platform.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author MungDong
 * @create 2024-03-20-23:06
 */
@Data
public class UserDO {
    private Long id;
    private String username;
    private String password;
    private Integer permission;
    private String nickName;
    private String phoneNumber;
    private Date createdAt;
    private Integer status;
    private String avatar;
    private Date dateOfBirth;
    private String usedInvitationCode;
}