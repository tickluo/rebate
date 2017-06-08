package org.sixcity.util;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;

import java.util.Map;

/**
 * http-request封装
 */
public class HttpUtils {

    public static JSONObject getFormPost(String url, Map<String, Object> postData) {
        if (postData == null || postData.size() == 0) {
            return JSONObject.parseObject(HttpRequest.post(url).body());
        }
        return JSONObject.parseObject(HttpRequest.post(url).form(postData).body());
    }

    public static JSONObject getJsonPost(String url, Map<String, Object> postData) {
        if (postData == null || postData.size() == 0) {
            return JSONObject.parseObject(HttpRequest.post(url).body());
        }
        return JSONObject.parseObject(HttpRequest.post(url).send(JSONObject.toJSONString(postData)).body());
    }

    public static byte[] getFilePost(String url, Map<String, Object> postData) {
        if (postData == null || postData.size() == 0) {
            return HttpRequest.post(url).body().getBytes();
        }
        return HttpRequest.post(url).send(JSONObject.toJSONString(postData)).bytes();
    }
}
