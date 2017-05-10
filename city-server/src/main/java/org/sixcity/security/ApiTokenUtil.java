package org.sixcity.security;

import org.sixcity.domain.dto.view.MerchantUser;
import org.sixcity.util.MD5Utils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ApiTokenUtil {

    private static Map<String, String> AppTokenClient = new HashMap<>();

    public static boolean validateSignature(MerchantUser user, String signature) {
        return signature.equals(generateSignature(user));
    }

    public static String generateToken(String appKey) {
        String appToken = "sckey-" + UUID.randomUUID().toString() + new Date().getTime();
        if (AppTokenClient.containsKey(appKey)) {
            AppTokenClient.replace(appKey, appToken);
        } else {
            AppTokenClient.put(appKey, appToken);
        }
        return appToken;
    }

    public static boolean validateToken(String token) {
        return AppTokenClient.containsValue(token);
    }

    public static String getAppKeyByToken(String token) {
        if (validateToken(token)) {
            for (Map.Entry entry : AppTokenClient.entrySet()) {
                if (token.equals(entry.getValue())) {
                    return entry.getKey().toString();
                }
            }
        }
        return null;
    }

    /**
     * 商户约束签名加密
     *
     * @param user
     * @return
     */
    private static String generateSignature(MerchantUser user) {
        String originalString = user.getAppKey() + user.getUsername() + user.getAppSecret();
        return MD5Utils.encode(originalString);
    }
}
