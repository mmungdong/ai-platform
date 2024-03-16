package com.platform.domain.auth;

import com.alibaba.fastjson.annotation.JSONField;
import com.platform.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MungDong
 * @create 2024-03-16-14:42
 */
@Data
@NoArgsConstructor
public class LoginUserCompare implements UserDetails {

    private User user;

    private List<String> permissions;

    public LoginUserCompare(User user){
        this.user=user;
    }

    public LoginUserCompare(User user, ArrayList<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
