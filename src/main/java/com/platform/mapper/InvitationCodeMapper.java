package com.platform.mapper;

import com.platform.domain.entity.InvitationCodeDO;
import com.platform.domain.vo.InvitationCodeVO;
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
    InvitationCodeDO findCode(@Param("code") String code);

    @Insert("insert into sys_invitation_code (id,code,status,create_time) values(#{id},#{code},#{status},#{createTime})")
    //加入该注解可以保存对象后，查看对象插入id
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void insertCode(InvitationCodeDO invitationCode);

    @Update("update sys_invitation_code c set status='CANCEL' where code = #{code} ")
    int discardInvitationCode(String code);

    @Select(value = "select c.id,c.code,c.status from sys_invitation_code c order by id desc limit 1")
    InvitationCodeVO findLastCode();

    @Select(value = "select c.id,c.code,c.status from sys_invitation_code c order by id desc")
    List<InvitationCodeVO> listCode();
}
