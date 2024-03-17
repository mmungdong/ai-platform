package com.platform.config;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author MungDong
 * @create 2024-03-16-15:45
 */
public class BCryptPasswordEncoderTest {

    public static void main(String[] args) {
        String plainPassword = "000";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 生成随机的盐值
        String salt = "";

        // 使用盐值加密密码
        String hashedPassword = passwordEncoder.encode(plainPassword);

        System.out.println("明文密码: " + plainPassword);
        System.out.println("盐值: " + salt);
        System.out.println("加密后的密码: " + hashedPassword);
    }
}