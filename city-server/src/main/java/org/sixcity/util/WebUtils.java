package org.sixcity.util;

import component.file.FileOperator;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;

public final class WebUtils {

    private FileOperator fileOperator;


    private WebUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 获取登录用户对象
     */
    public static <T extends UserDetails> T getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (T) authentication.getPrincipal();
    }
}
