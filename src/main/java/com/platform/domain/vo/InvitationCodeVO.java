package com.platform.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MungDong
 * @create 2024-03-23-16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationCodeVO {
    private Long id;
    private String code;
    private String status;
}
