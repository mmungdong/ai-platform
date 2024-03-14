package com.platform.service.impl;

import com.platform.domain.InvitationCode;
import com.platform.domain.Result;
import com.platform.domain.User;
import com.platform.mapper.InvitationCodeMapper;
import com.platform.mapper.UserMapper;
import com.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InvitationCodeMapper invitationCodeMapper;

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    public Result regist(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        String code = user.getCode();
        if (code != null && !"".equals(code)) {
            InvitationCode invitationCode = invitationCodeMapper.findCode(code);
            if(invitationCode!=null){
                User existUser = userMapper.findUserByName(user.getUsername());
                if(existUser != null){
                    //如果用户名已存在
                    result.setMsg("用户名已存在");
                }else{
                    userMapper.regist(user);
                    //System.out.println(user.getId());
                    result.setMsg("注册成功");
                    result.setSuccess(true);
                    result.setDetail(user);
                    //作废此邀请码
                    invitationCodeMapper.updateCodeStatus(invitationCode);
                }
            }else{
                result.setMsg("无效邀请码");
            }
        }else {
            result.setMsg("请输入邀请码");
        }
        return result;
    }
    /**
     * 登录
     * @param user 用户名和密码
     * @return Result
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Integer userId= userMapper.login(user);
            if(userId == null){
                result.setMsg("用户名或密码错误");
            }else{
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
