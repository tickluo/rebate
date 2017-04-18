package org.sixcity.util;

import org.sixcity.service.serviceimpl.AdminService;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;
import java.util.Collection;

public final class WebUtils {


    private WebUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static String getUserAuthority(Collection<? extends GrantedAuthority> authorities){
        if (authorities.stream().findAny().isPresent()) {
            return authorities.stream().findAny().get().toString();
        } else {
            throw new DisabledException("用户没有权限");
        }
    }

    /**
     * 获取当前用户对象
     */
    public static <T extends UserDetails> T getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ("ROLE_SUPER_ADMIN".equals(getUserAuthority(authentication.getAuthorities()))) {
            return (T) AdminService.getAdminAsMerchant() == null ? (T) authentication.getPrincipal() : (T) AdminService.getAdminAsMerchant();
        }
        return (T) authentication.getPrincipal();
    }

    /**
     * 获取真实登录用户对象
     */
    public static <T extends UserDetails> T getActualUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (T) authentication.getPrincipal();
    }
}
