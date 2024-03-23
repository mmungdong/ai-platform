package com.platform.mapper;

import com.platform.domain.entity.UserDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;


/**
 * mapper的具体表达式
 */
@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {

    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @param username
     * @return
     */
    @Select(value = "select u.username,u.password, u.permission, u.status from sys_user u where u.username=#{username}")
    @Results
            ({
                    @Result(property = "username",column = "username"),
                    @Result(property = "password",column = "password"),
                    @Result(property = "status",column = "status"),
                    @Result(property = "permission",column = "permission", jdbcType = JdbcType.TINYINT)
            })
    UserDO findUserByName(@Param("username") String username);

    /**
     * 注册  插入一条user记录
     * @param user
     * @return
     */
    @Insert("insert into sys_user (id, username, password, created_at, used_invitation_code) values(#{id},#{username},#{password},#{createdAt}, #{usedInvitationCode})")
    //加入该注解可以保存对象后，查看对象插入id
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void regist(UserDO user);

    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select u.id from sys_user u where u.username = #{username} and password = #{password}")
    Integer login(UserDO user);
}
