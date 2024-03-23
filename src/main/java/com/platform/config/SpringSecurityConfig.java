package com.platform.config;


import com.platform.filter.UserTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author MungDong
 * @create 2024-03-16-14:31
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Resource
    private UserTokenFilter userTokenFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                // api
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/regist").permitAll()
                .antMatchers("/logout").permitAll()

                // startic page
                .antMatchers("/").permitAll()
                .antMatchers("/company/index.html").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error/*.html").permitAll()
                // auth
                .antMatchers("/index.html").hasAnyAuthority("ADMIN", "USER", "SUPER_ADMIN")
                .anyRequest().authenticated();

        http.
                addFilterBefore(userTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.
                exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

}
