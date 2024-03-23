--- create db
CREATE DATABASE IF NOT EXISTS ai_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE ai_platform;

-- ai_platform.sys_user definition
CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
                            `username` varchar(100) NOT NULL unique,
                            `password` varchar(100) DEFAULT NULL,
                            `permission` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0：普通用户 1：超级管理员 2：管理员',
                            `nick_name` varchar(255) DEFAULT NULL,
                            `phone_number` varchar(20) DEFAULT NULL,
                            `created_at` timestamp NULL DEFAULT NULL,
                            `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                            `status` tinyint(1) DEFAULT '1' COMMENT '1: 正常； 0：禁用',
                            `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                            `date_of_birth` date DEFAULT NULL,
                            `used_invitation_code` varchar(100) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO ai_platform.sys_user (username,password,permission) VALUES
    ('admin','$2a$10$2rCNlY/4xp3qH7DyM4U0weOv9bNXnnMeB7LkrQ9.b/BYQ3WtDhNtO', '1');

-- ai_platform.sys_invitation_code definition

CREATE TABLE `sys_invitation_code` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `code` varchar(100) DEFAULT NULL,
                                       `status` varchar(100) DEFAULT NULL,
                                       `created_uid` int(11) DEFAULT NULL,
                                       `used_uid` int(11) DEFAULT NULL,
                                       `create_time` timestamp NULL DEFAULT NULL,
                                       `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO ai_platform.sys_invitation_code (code,status) VALUES
                                                              ('XXXZZZ','CANCEL'),
                                                              ('3DAQWP','NORMAL'),
                                                              ('MJZWZN','NORMAL'),
                                                              ('YQV7TL','NORMAL'),
                                                              ('2ALVV5','NORMAL'),
                                                              ('8FYNP3','NORMAL'),
                                                              ('EWK3LM','NORMAL'),
                                                              ('KCX23Y','NORMAL'),
                                                              ('UZHKY2','NORMAL'),
                                                              ('B7AG88','NORMAL');
