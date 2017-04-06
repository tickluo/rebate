package org.sixcity.web;

import com.alibaba.fastjson.JSONObject;
import model.Result;
import model.ResultCode;
import org.sixcity.domain.User;
import org.sixcity.domain.dto.post.LoginForm;
import org.sixcity.domain.dto.post.RegisterUserForm;

import org.sixcity.security.service.AuthService;
import org.sixcity.service.serviceimpl.CaptchaService;
import org.sixcity.service.serviceimpl.ShortMessageService;
import org.sixcity.service.serviceimpl.UserService;
import org.sixcity.util.CookieUtils;
import org.sixcity.util.MessageHandleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import util.RandomUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/auth", method = RequestMethod.GET)
public class AuthController {

    private final ShortMessageService shortMessageService;
    private final UserService userService;
    private final AuthService authService;
    private final CaptchaService captchaService;

    @Autowired
    public AuthController(ShortMessageService shortMessageService,
                          UserService userService,
                          AuthService authService,
                          CaptchaService captchaService) {
        this.shortMessageService = shortMessageService;
        this.userService = userService;
        this.authService = authService;
        this.captchaService = captchaService;
    }

    @Value("${cookie.token.name}")
    private String cookieTokenName;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "auth/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "auth/register";
    }

    @ResponseBody
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public Result registerPost(@RequestBody @Valid RegisterUserForm user, BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage("用户名已注册");
        }
        // here valid shortMessageCode
        if (shortMessageService.validCaptcha(user.getCode())) {
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage("验证码错误，请重新输入");
        }
        //build User Entity
        User userEntity = JSONObject.parseObject(JSONObject.toJSONString(user), User.class);
        userEntity.setAppkey(RandomUtils.uuid());
        //do register
        if (userService.registerUser(userEntity) <= 0) {
            return Result.createErrorResult(ResultCode.DAO_ERROR)
                    .setMessage("注册失败，请重试");
        }
        return Result.createSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public Result loginPost(@RequestBody @Valid LoginForm user,
                            HttpServletResponse httpServletResponse,
                            BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }
        // here valid captcha
        if (captchaService.validCaptcha(user.getCode())) {
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage("验证码错误，请重新输入");
        }

        //do login
        /*if (!userService.checkLogin(user.getUsername(), user.getPassword())) {
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage("密码错误，请重新输入");
        }*/
        final String token = authService.login(user.getUsername(), user.getPassword());
        CookieUtils.create(httpServletResponse, cookieTokenName, token);
        return Result.createSuccessResult();
    }
}
