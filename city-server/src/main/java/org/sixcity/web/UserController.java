package org.sixcity.web;

import com.alibaba.fastjson.JSONObject;
import model.Result;
import model.ResultCode;
import org.sixcity.domain.dto.post.ResetPasswordForm;
import org.sixcity.domain.dto.post.UserInfoForm;
import org.sixcity.security.model.JwtUser;
import org.sixcity.security.service.AuthServiceImpl;
import org.sixcity.service.impl.AdminService;
import org.sixcity.service.impl.ProductService;
import org.sixcity.service.impl.UserService;

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
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping(value = "user")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_SUPER_ADMIN')")
public class UserController {

    private final UserService userService;
    private final AuthServiceImpl authService;
    private final ProductService productService;
    private final AdminService adminService;

    @Value("${cookie.token.name}")
    private String cookieTokenName;

    @Autowired
    public UserController(UserService userService,
                          AuthServiceImpl authService,
                          ProductService productService,
                          AdminService adminService) {
        this.userService = userService;
        this.authService = authService;
        this.productService = productService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    public String userInfo() {
        return "user/userInfo";
    }

    @RequestMapping(value = "resetPassword", method = RequestMethod.GET)
    public String resetPassword() {
        return "user/resetPassword";
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @RequestMapping(value = "selectMerchant", method = RequestMethod.GET)
    public String selectMerchant() {
        return "/user/selectMerchant";
    }

    @RequestMapping(value = "getUserInfoByToken", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserInfoByToken() {
        JwtUser jwtUser = WebUtils.getActualUser();

        return Result.createSuccessResult(userService.findMerchantUserById(jwtUser.getId()), "用户信息");
    }

    @RequestMapping(value = "getUserInfoIncludeAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserInfoIncludeAdmin() {

        JwtUser jwtUser = WebUtils.getCurrentUser();

        return Result.createSuccessResult(userService.findMerchantUserById(jwtUser.getId()), "用户信息");
    }

    @RequestMapping(value = "getAllMerchant", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @ResponseBody
    public Result getAllMerchant() {
        return Result.createSuccessResult(userService.getAllMerchant(), "商户列表");
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
            return Result.createErrorResult(ResultCode.SERVICE_ERROR)
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
        JwtUser jwtUser = WebUtils.getActualUser();
        //do update
        if (userService.modifyUserInfoById(jwtUser.getId(), form.getEmail(), form.getPhone()) <= 0) {
            return Result.createErrorResult(ResultCode.DAO_ERROR)
                    .setMessage("修改失败，请重试");
        }

        return Result.createSuccessResult("修改成功");
    }

    @RequestMapping(value = "getUserRebateAmount", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserRebateAmount() throws ParseException {
        JwtUser jwtUser = WebUtils.getCurrentUser();

        return Result.createSuccessResult(
                productService.getUserRebateAmount(jwtUser.getId(), null, null), "用户可申请返利"
        );
    }

    @RequestMapping(value = "adminSelectMerchant", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSelectMerchant(@RequestBody JSONObject user) {
        adminService.setAdminAsMerchant(authService.getUserByUsername(user.get("username").toString()));
        return Result.createSuccessResult();
    }

}
