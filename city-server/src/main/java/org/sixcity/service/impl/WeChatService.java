package org.sixcity.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.sixcity.util.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//TODO:check global shared source accessToken & check request to wechat should be protected
@Service
public class WeChatService {

    private final String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/" +
            "token?grant_type=client_credential&appid={0}&secret={1}";
    private final String getQRCodeUrl = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token={0}";
    private final String getQRCodePath = "page/scanCode/detail/detail?code={0}";
    private final int QRCodeWidth = 430;

    private Map<String, Object> accessToken = new HashMap<String, Object>();

    @Value("${wechat.account.app-id}")
    private String appId;
    @Value("${wechat.account.app-secret}")
    private String appSecret;

    public void checkToken() {
        if (accessToken.containsKey("expire") &&
                accessToken.get("expire") != null &&
                Long.parseLong(accessToken.get("expire").toString()) > new Date().getTime()) {// && accessToken.get("expire") > new Date().getTime()){
            return;
        }
        String postUrl = MessageFormat.format(getTokenUrl, appId, appSecret);
        JSONObject result = HttpUtils.getJsonPost(postUrl, null);
        accessToken.put("token", result.getString("access_token"));
        accessToken.put("expire", new Date(System.currentTimeMillis() + Long.parseLong(result.getString("expires_in")) * 1000).getTime());
    }

    public byte[] getQRCode(String shortUrl) {
        checkToken();
        String postUrl = MessageFormat.format(getQRCodeUrl, accessToken.get("token"));
        Map<String, Object> postData = new HashMap<>();
        postData.put("path", MessageFormat.format(getQRCodePath, shortUrl));
        postData.put("width", QRCodeWidth);
        return HttpUtils.getFilePost(postUrl, postData);
    }
}
