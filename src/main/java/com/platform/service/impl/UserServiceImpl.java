package com.platform.service.impl;

import com.platform.domain.auth.LoginUserCompare;
import com.platform.domain.InvitationCode;
import com.platform.domain.Result;
import com.platform.domain.User;
import com.platform.mapper.InvitationCodeMapper;
import com.platform.mapper.UserMapper;
import com.platform.service.UserService;
import com.platform.utils.CustomerJwtUtil;
import com.platform.utils.ResponseObj;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private InvitationCodeMapper invitationCodeMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplate redisTemplate;

    public static final long exprTime=60*1000*60;

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
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public ResponseObj login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate))
        {
            return ResponseObj.fail("账号或密码错误!");
        }
        LoginUserCompare principal = (LoginUserCompare) authenticate.getPrincipal();
        String token = CustomerJwtUtil.buildUserLoginTokenByUserID(principal.getUser().getId());

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("uid:"+principal.getUser().getId(),principal,exprTime, TimeUnit.MILLISECONDS);

        return ResponseObj.success(token);
    }

    public ResponseObj logout(User user) {
        redisTemplate.delete("uid:" + user.getId());
        return ResponseObj.success("成功退出！");
    }

}
