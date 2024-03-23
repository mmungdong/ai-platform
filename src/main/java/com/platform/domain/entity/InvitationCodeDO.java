package com.platform.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author MungDong
 * @create 2024-03-20-23:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCodeDO {
    private Long id;
    private String code;
    private String status;
    private Long createdUid;
    private Long usedUid;
    private Date createTime;
    private Date updateTime;
}