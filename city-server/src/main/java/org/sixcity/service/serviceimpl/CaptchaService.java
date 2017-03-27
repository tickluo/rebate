package org.sixcity.service.serviceimpl;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.gimpy.FishEyeGimpyRenderer;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class CaptchaService {

    public Captcha generateCaptcha(String uuid) {
        int captchaW = 200;
        int captchaH = 60;
        return new Captcha.Builder(captchaW, captchaH)
                .addText().addBackground(new GradiatedBackgroundProducer())
                .gimp(new FishEyeGimpyRenderer())
                .build();
    }

    //将验证码key，及验证码的图片返回
    public byte[] sendCaptcha(Captcha captcha) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            ImageIO.write(captcha.getImage(), "png", bao);
            return bao.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean validCaptcha(String captcha) {

        return false;
    }
}
