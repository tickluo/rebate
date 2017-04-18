package org.sixcity.service.serviceimpl;

import org.sixcity.security.model.JwtUser;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private static JwtUser jwtUser;

    @CachePut(value = "adminAsMerchant", key = "#user.username")
    public void setAdminAsMerchant(JwtUser user) {
        jwtUser = user;
    }

    @Cacheable(value = "adminAsMerchant", key = "user.username")
    public static JwtUser getAdminAsMerchant() {
        return jwtUser;
    }

    @CacheEvict(value = "adminAsMerchant")
    public void remove(Long id) {
        jwtUser = null;
    }

}
