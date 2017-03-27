package org.sixcity.service.serviceimpl;

import org.springframework.stereotype.Service;

@Service
public class ShortMessageService {

    //将验证码key，及验证码的图片返回
    public void sendCaptcha() {

    }

    public boolean validCaptcha(String captcha) {
        return false;
    }
}
