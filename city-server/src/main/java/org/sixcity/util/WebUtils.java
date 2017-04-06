package org.sixcity.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class WebUtils {

    /**
     * 获取登录用户对象
     * */
    public static <T extends UserDetails> T getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (T) authentication.getPrincipal();
    }
}
