package com.platform.domain.auth;

/**
 * @author MungDong
 * @create 2024-03-16-14:42
 *  [deprecated]
 */
//@Data
//@NoArgsConstructor
//public class LoginUserCompare implements UserDetails {
//
//    private User user;
//
//    private List<String> permissions;
//
//    public LoginUserCompare(User user){
//        this.user=user;
//    }
//
//    public LoginUserCompare(User user, ArrayList<String> permissions) {
//        this.user = user;
//        this.permissions = permissions;
//    }
//
//    public List<String> getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(List<String> permissions) {
//        this.permissions = permissions;
//    }
//
//    @JSONField(serialize = false)
//    private List<SimpleGrantedAuthority> authorities;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (authorities != null) {
//            return authorities;
//        }
//        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
