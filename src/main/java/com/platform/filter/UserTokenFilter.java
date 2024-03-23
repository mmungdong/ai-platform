package com.platform.filter;

import com.alibaba.fastjson.JSON;
import com.platform.domain.dto.LoginUserDetails;
import com.platform.exception.exceptionImpl.UseInputException;
import com.platform.service.impl.UserServiceImpl;
import com.platform.utils.auth.CustomerJwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author MungDong
 * @create 2024-03-16-14:34
 */
@Component
public class UserTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ai-platform-username")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (!StringUtils.hasText(token))
        {
            filterChain.doFilter(request,response);
            return;
        }

        String username;
        try{
            username = CustomerJwtUtil.parseUserLoginToken(token);
            if (username==null)
            {
                throw new UseInputException("the token status is illegally!");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        //redis用户过期判断
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get("ai-platform-username:" + username);
        LoginUserDetails user;
        if (o!=null)
        {
            user= JSON.parseObject(JSON.toJSONString(o), LoginUserDetails.class);
            //刷新过期时间
            redisTemplate.expire("ai-platform-username:" + username,System.currentTimeMillis()+ UserServiceImpl.exprTime, TimeUnit.MILLISECONDS);
        }else
        {
            filterChain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken userAuthentication=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);

        // pass
        filterChain.doFilter(request,response);
    }
}