package com.platform.service.impl;

import com.platform.domain.Result;
import com.platform.domain.dto.LoginUserDetails;
import com.platform.domain.entity.InvitationCodeDO;
import com.platform.domain.entity.UserDO;
import com.platform.domain.vo.LoginUserVO;
import com.platform.domain.vo.RegistUserVO;
import com.platform.mapper.InvitationCodeMapper;
import com.platform.mapper.UserMapper;
import com.platform.service.UserService;
import com.platform.utils.SnowflakeIdGenerator;
import com.platform.utils.auth.CustomerJwtUtil;
import com.platform.utils.resp.ResponseObj;
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
import java.util.Date;
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

    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;

    @Resource
    private RedisTemplate redisTemplate;

    public static final long exprTime=60*1000*60;

    /**
     * 注册
     * @param user 参数封装
     * @return Result
     */
    public Result regist(RegistUserVO user) {
        Result result = new Result();
        result.setSuccess(false);
        String code = user.getCode();
        if (code != null && !"".equals(code)) {
            InvitationCodeDO invitationCode = invitationCodeMapper.findCode(code);
            if(invitationCode!=null && (Objects.equals(invitationCode.getStatus(), "NORMAL"))){
                if(!Objects.isNull(userMapper.findUserByName(user.getUsername()))){
                    //如果用户名已存在
                    result.setMsg("用户名已存在");
                }else{
                    UserDO userDO = new UserDO();
                    userDO.setId(snowflakeIdGenerator.generateId());
                    userDO.setUsername(user.getUsername());
                    userDO.setPassword(passwordEncoder.encode(user.getPassword()));
                    userDO.setUsedInvitationCode(user.getCode());
                    userDO.setCreatedAt(new Date(System.currentTimeMillis()));
                    userMapper.regist(userDO);

                    result.setMsg("注册成功");
                    result.setSuccess(true);
                    result.setData(user);
                    //作废此邀请码
                    invitationCodeMapper.discardInvitationCode(invitationCode.getCode());
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
    public ResponseObj login(LoginUserVO user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate))
        {
            return ResponseObj.badCredentialsError("用户名或密码错误!");
        }
        LoginUserDetails principal = (LoginUserDetails) authenticate.getPrincipal();
        String token = CustomerJwtUtil.buildUserLoginTokenByUserName(principal.getUsername());

        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("ai-platform-username:"+principal.getUsername(),principal,exprTime, TimeUnit.MILLISECONDS);

        return ResponseObj.success(token);
    }

    public ResponseObj logout(LoginUserVO user) {
        redisTemplate.delete("ai-platform-username:" + user.getUsername());
        return ResponseObj.success("成功退出！");
    }

}
