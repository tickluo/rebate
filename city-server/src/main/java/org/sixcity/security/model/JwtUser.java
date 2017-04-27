package org.sixcity.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String appId;
    private final String username;
    private final String password;
    private Collection<SimpleGrantedAuthority> authorities;

    public JwtUser(
            Long id,
            String appId,
            String username,
            String password,
            Collection<SimpleGrantedAuthority> authorities) {
        this.id = id;
        this.appId = appId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    //返回分配给用户的角色列表

   /* @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(SecurityConst.SECURITY_ROLE_USER));
        return authorities;
    }*/

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getAppId() {
        return appId;
    }

    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }
}
