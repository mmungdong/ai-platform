package com.platform.service.authentication;

import com.platform.domain.User;
import com.platform.domain.auth.LoginUserCompare;
import com.platform.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
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

        User user = userMapper.findUserByName(username);
        if (Objects.isNull(user))
        {
            throw new RuntimeException("用户不存在!");
        }
        ArrayList<String> permissions = new ArrayList<>(Arrays.asList("USER"));
        if(user.getUsername() == "admin") {
            permissions.add("ADMIN");
        }


        return new LoginUserCompare(user, permissions);
    }
}
