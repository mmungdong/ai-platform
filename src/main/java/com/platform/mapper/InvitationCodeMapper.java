package com.platform.mapper;

import com.platform.domain.InvitationCode;
import com.platform.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * mapper的具体表达式
 */
@Mapper
@Repository
public interface InvitationCodeMapper {

    // @Select(value = "select c.code,c.status from sys_invitation_code c where c.code=#{code} and c.status='NORMAL' ")
    @Select(value = "select c.code,c.status from sys_invitation_code c where c.code=#{code}")
    @Results
            ({@Result(property = "code",column = "code"),
              @Result(property = "status",column = "status")})
    InvitationCode findCode(@Param("code") String code);

    @Insert("insert into sys_invitation_code values(#{id},#{code},#{status})")
    //加入该注解可以保存对象后，查看对象插入id
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void insertCode(InvitationCode invitationCode);

    @Update("update sys_invitation_code c set status='CANCEL' where code = #{code} ")
    Integer updateCodeStatus(InvitationCode invitationCode);

    @Select(value = "select c.id,c.code,c.status from sys_invitation_code c order by id desc limit 1")
    InvitationCode findLastCode();

    @Select(value = "select c.id,c.code,c.status from sys_invitation_code c order by id desc")
    List<InvitationCode> listCode();
}
