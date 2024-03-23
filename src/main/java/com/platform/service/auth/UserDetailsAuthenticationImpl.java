package com.platform.service.auth;

import com.platform.domain.dto.LoginUserDetails;
import com.platform.domain.entity.UserDO;
import com.platform.domain.vo.LoginUserVO;
import com.platform.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author MungDong
 * @create 2024-03-16-15:18
 */
@Service
public class UserDetailsAuthenticationImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDO user = userMapper.findUserByName(username);
        if (Objects.isNull(user))
        {
            throw new RuntimeException("该用户不存在!");
        }

        ArrayList<String> permissions = new ArrayList<>();
        if (Objects.isNull(user.getPermission())) {
            throw new RuntimeException("该用户异常，请联系管理员！");
        }
        switch (user.getPermission()){
            case 0:
                permissions.add("USER");
            case 1:
                permissions.add("SUPER_ADMIN");
            case 2:
                permissions.add("ADMIN");
        }

        if (user.getStatus() == 0) throw new RuntimeException("该用户已被锁定，请联系管理员!");
        else {
            return new LoginUserDetails((new LoginUserVO(user.getUsername(), user.getPassword())), permissions);
        }

    }
}
