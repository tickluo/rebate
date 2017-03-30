package org.sixcity.web;

import model.Result;
import model.ResultCode;
import org.sixcity.domain.dto.post.ResetPasswordForm;
import org.sixcity.domain.dto.post.UserInfoForm;
import org.sixcity.security.JwtTokenUtil;
import org.sixcity.security.model.JwtUser;
import org.sixcity.service.serviceimpl.UserService;

import org.sixcity.util.MessageHandleUtils;
import org.sixcity.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Value("${cookie.token.name}")
    private String cookieTokenName;

    @Autowired
    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    public String userInfo() {
        return "user/userInfo";
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.GET)
    public String resetPassword() {
        return "user/resetPassword";
    }

    @RequestMapping(value = "getUserInfoByToken", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserInfoByToken() {

        JwtUser jwtUser = WebUtils.getCurrentUser();

        return Result.createSuccessResult(userService.findById(jwtUser.getId()), "用户信息");
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPasswordPost(@RequestBody @Valid ResetPasswordForm form, BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }
        JwtUser jwtUser = WebUtils.getCurrentUser();
        if (!userService.checkLogin(jwtUser.getUsername(), form.getOldPassword())) {
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage("旧密码错误，请重新输入");
        }

        //do update
        if (userService.modifyPasswordById(jwtUser.getId(), form.getNewPassword()) <= 0) {
            return Result.createErrorResult(ResultCode.DAO_ERROR)
                    .setMessage("修改失败，请重试");
        }

        return Result.createSuccessResult("修改成功");
    }


    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result userInfoPost(@RequestBody @Valid UserInfoForm form, BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }
        JwtUser jwtUser = WebUtils.getCurrentUser();
        //do update
        if (userService.modifyUserInfoById(jwtUser.getId(), form.getEmail(), form.getPhone()) <= 0) {
            return Result.createErrorResult(ResultCode.DAO_ERROR)
                    .setMessage("修改失败，请重试");
        }

        return Result.createSuccessResult("修改成功");
    }
}
