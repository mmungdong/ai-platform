package com.platform.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MungDong
 * @create 2024-03-23-14:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistUserVO {
    private String username;
    private String password;
    private String code;
}
