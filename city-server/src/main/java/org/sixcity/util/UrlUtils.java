package org.sixcity.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UrlUtils {

    public static String generateShortUrl(String url) {
        try {
            Map<String, Object> form = new HashMap<String, Object>();
            form.put("url", url);
            //use baidu api to convert shortUrl
            JSONObject json = HttpUtils.getFormPost("http://dwz.cn/create.php", form);
            return json.getString("tinyurl");
        } catch (Exception e) {
            return "Error";
        }

    }
}
