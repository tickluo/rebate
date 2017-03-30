package org.sixcity.web;

import model.Result;
import org.sixcity.security.model.JwtUser;
import org.sixcity.util.WebUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "bank")
@PreAuthorize("hasRole('ROLE_USER')")
public class BankController {

    @RequestMapping(value = "bankInfo", method = RequestMethod.GET)
    public String bankInfo() {
        return "user/bankInfo";
    }

    @RequestMapping(value = "getBankList", method = RequestMethod.POST)
    @ResponseBody
    public Result getBankListByUserId() {

        JwtUser jwtUser = WebUtils.getCurrentUser();

        return Result.createSuccessResult("", "用户信息");
    }
}
