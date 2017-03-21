package org.sixcity.service;


import cn.apiclub.captcha.Captcha;

public interface ICaptchaService {
    /**
     * 获取验证码
     */
    byte[] getCaptcha();

    /**
     * 验证验证码
     */
    Boolean validCaptcha(String captcha) ;

}
