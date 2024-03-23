package com.platform.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MungDong
 * @create 2024-03-23-11:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVO {
    private String username;
    private String password;
}
