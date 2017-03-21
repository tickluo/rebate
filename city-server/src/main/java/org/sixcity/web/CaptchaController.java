package org.sixcity.web;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.sixcity.service.serviceimpl.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import cn.apiclub.captcha.Captcha;

@RestController
@RequestMapping("captcha")
public class CaptchaController {

    private final RedisTemplate<String, String> redisTemplate;

    private final CaptchaService captchaService;

    @Autowired
    public CaptchaController(CaptchaService captchaService, RedisTemplate<String, String> redisTemplate) {
        this.captchaService = captchaService;
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getCaptcha(HttpServletResponse response) {
        String uuid = UUID.randomUUID().toString();

        Captcha captcha = captchaService.generateCaptcha(uuid);

        //将验证码以<key,value>形式缓存到redis
        int captchaExpires = 3 * 60;
        redisTemplate.opsForValue().set(uuid, captcha.getAnswer(), captchaExpires, TimeUnit.SECONDS);

        Cookie cookie = new Cookie("CaptchaCode", uuid);
        response.addCookie(cookie);
        return captchaService.sendCaptcha(captcha);
    }

    @RequestMapping(value = "/getShortMsg", method = RequestMethod.GET)
    public void sendShortMessage() {

    }
}
