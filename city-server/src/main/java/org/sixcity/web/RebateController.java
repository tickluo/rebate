package org.sixcity.web;

import model.Result;
import model.ResultCode;
import org.sixcity.domain.CashOut;
import org.sixcity.domain.User;
import org.sixcity.domain.dto.post.RebateCashForm;
import org.sixcity.security.model.JwtUser;
import org.sixcity.service.serviceimpl.UserService;
import org.sixcity.util.MessageHandleUtils;
import org.sixcity.util.WebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/rebate")
@PreAuthorize("hasRole('ROLE_USER')")
public class RebateController {

    private final UserService userService;

    @Autowired
    public RebateController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "rebateCash", method = RequestMethod.GET)
    public String rebateCash() {
        return "rebate/rebateCash";
    }

    @RequestMapping(value = "cashRecord", method = RequestMethod.GET)
    public String cashRecord() {
        return "rebate/cashRecord";
    }

    @RequestMapping(value = "rebateSite", method = RequestMethod.GET)
    public String rebateSite() {
        return "rebate/rebateSite";
    }

    @RequestMapping(value = "doRebateCash", method = RequestMethod.POST)
    @ResponseBody
    public Result rebateCashPost(@RequestBody @Valid RebateCashForm form, BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }
        JwtUser jwtUser = WebUtils.getCurrentUser();
        //check user balance
        User userEntity = userService.findById(jwtUser.getId());
        if (userEntity.getAmount().compareTo(form.getApplyMoney()) < 0) {
            return Result.createErrorResult(
                    ResultCode.SERVICE_ERROR, "用户余额不足，余额还剩："
                            .concat(userEntity.getAmount().toString())
            );
        }

        //build cash out Entity
        CashOut cashOutEntity = new CashOut();
        cashOutEntity.setUserId(jwtUser.getId());
        cashOutEntity.setBankId(form.getBankId());
        cashOutEntity.setApplyMoney(form.getApplyMoney());
        return Result.createSuccessResult("添加成功");
    }
}
