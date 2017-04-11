package org.sixcity.security.model;

import org.sixcity.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhone(),
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    /**
     * 权限转换
     * @return 权限列表
     */
    private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(String roles) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roles));

        return authorities;
    }
}