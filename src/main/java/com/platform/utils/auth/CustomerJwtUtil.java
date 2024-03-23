package com.platform.utils.auth;

import com.platform.exception.exceptionImpl.UseInputException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;

/**
 * @author MungDong
 * @create 2024-03-16-14:35
 */
@Component
public class CustomerJwtUtil {
    private static String SIGNATURE;

    @Value("${jwt.signature.username}")
    private String signature;//签名

    @PostConstruct
    public void setSIGNATURE(){
        //Bean的创建流程 new Bean ->属性注入 -> 初始化前 @PostConstruct执行 ->初始化中 实现了InitializingBean的方法 ->初始化后aop
        //->放入单例池
        SIGNATURE=signature;
    }

    private static final long expreTime=60*1000*60;

    private static final HashMap<String, Object> jwtHeader = new HashMap<>();
    static  {
        jwtHeader.put("type", "JWT");
        jwtHeader.put("algorithm", "HS256");
    }

    public static String buildUserLoginTokenByUserName(String username){
        String token=null;
        if (!StringUtils.isEmpty(username))
        {
            HashMap<String, Object> userInfo = new HashMap<>();
            userInfo.put("ai-platform-username",username);
            token = Jwts.builder()
                    .setHeader(jwtHeader)
                    .setClaims(userInfo)
                    .signWith(SignatureAlgorithm.HS256, SIGNATURE)
                    .setExpiration(new Date(System.currentTimeMillis()+expreTime))
                    .compact();
        }
        return token;
    }

    public static String parseUserLoginToken(String token)
    {
        if (!StringUtils.hasText(token))
        {
            throw new UseInputException("受解析的token非法!");
        }
        Claims body = Jwts.parser().setSigningKey(SIGNATURE).parseClaimsJws(token).getBody();
        String username = (String)body.get("ai-platform-username");
        if (StringUtils.isEmpty(username))
        {
            username="";
        }
        return username;
    }

}