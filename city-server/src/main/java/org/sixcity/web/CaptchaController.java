package org.sixcity.web;

import java.util.UUID;

import org.sixcity.service.impl.CaptchaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.apiclub.captcha.Captcha;
import util.RandomUtils;

@RestController
@RequestMapping("captcha")
public class CaptchaController {

    //private final RedisTemplate<String, String> redisTemplate;
    private final CaptchaService captchaService;

    @Autowired
    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getCaptcha(HttpServletRequest request) {
        String uuid = RandomUtils.uuid();

        Captcha captcha = captchaService.generateCaptcha(uuid);

        //将验证码以<key,value>形式缓存到redis
       /* int captchaExpires = 3 * 60;
        redisTemplate.opsForValue().set(uuid, captcha.getAnswer(), captchaExpires, TimeUnit.SECONDS);*/
        HttpSession session = request.getSession();
        session.removeAttribute(Captcha.NAME);
        session.setAttribute(Captcha.NAME, captcha.getAnswer());
        session.setMaxInactiveInterval(5*60);

        //CookieUtils.create(response, Captcha.NAME, uuid);
        return captchaService.sendCaptcha(captcha);

    }

    @RequestMapping(value = "/getShortMsg", method = RequestMethod.GET)
    public void sendShortMessage() {

    }
}
