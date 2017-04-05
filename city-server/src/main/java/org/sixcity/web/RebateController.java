package org.sixcity.web;

import model.Result;
import model.ResultCode;
import org.sixcity.domain.Bank;
import org.sixcity.domain.CashOut;
import org.sixcity.domain.dto.post.RebateCashForm;
import org.sixcity.security.model.JwtUser;
import org.sixcity.util.MessageHandleUtils;
import org.sixcity.util.WebUtils;

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

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/rebate")
@PreAuthorize("hasRole('ROLE_USER')")
public class RebateController {

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
        //build cash out Entity
        JwtUser jwtUser = WebUtils.getCurrentUser();

        CashOut cashOutEntity = new CashOut();
        cashOutEntity.setUserId(jwtUser.getId());
        cashOutEntity.setBankId(form.getBankId());
        cashOutEntity.setApplyMoney(form.getApplyMoney());
        return Result.createSuccessResult("添加成功");
    }
}
