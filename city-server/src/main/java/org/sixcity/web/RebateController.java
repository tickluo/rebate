package org.sixcity.web;

import model.Result;
import model.ResultCode;
import org.sixcity.constant.state.CashOutConst;
import org.sixcity.domain.Bank;
import org.sixcity.domain.CashOut;
import org.sixcity.domain.User;
import org.sixcity.domain.dto.post.RebateCashForm;
import org.sixcity.security.model.JwtUser;
import org.sixcity.service.serviceimpl.BankService;
import org.sixcity.service.serviceimpl.RebateService;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(value = "/rebate")
@PreAuthorize("hasRole('ROLE_USER')")
public class RebateController {

    private final UserService userService;
    private final BankService bankService;
    private final RebateService rebateService;

    @Autowired
    public RebateController(UserService userService, BankService bankService, RebateService rebateService) {
        this.userService = userService;
        this.bankService = bankService;
        this.rebateService = rebateService;
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

    @RequestMapping(value = "getUserRebateTimes", method = RequestMethod.POST)
    @ResponseBody
    public Result rebateTimes() {
        JwtUser jwtUser = WebUtils.getCurrentUser();
        return Result.createSuccessResult(rebateService.getUserRebateTimes(jwtUser.getId()),"提现次数");
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
        //check jwtUser if bankId valid
        List<Bank> bankList = bankService.findByUserId(jwtUser.getId());
        Optional<Bank> currentBank = bankList.stream()
                .filter(p -> Objects.equals(p.getId(), form.getBankId()))
                .findAny();
        if (!currentBank.isPresent()) {
            return Result.createErrorResult(
                    ResultCode.SERVICE_ERROR, "银行卡不存在");
        }
        //check user rebate times
        if(rebateService.getUserRebateTimes(jwtUser.getId()) >= 10){
            return Result.createErrorResult(
                    ResultCode.SERVICE_ERROR, "提现次数已经用完");
        }

        //build cash out Entity
        CashOut cashOutEntity = new CashOut();
        cashOutEntity.setUserId(jwtUser.getId());
        cashOutEntity.setBankId(form.getBankId());
        cashOutEntity.setAccountName(currentBank.get().getOpenAccountName());
        cashOutEntity.setBankName(currentBank.get().getBankName());
        cashOutEntity.setBankNum(currentBank.get().getBankNum());
        cashOutEntity.setPayTime(new Date(0));
        cashOutEntity.setState(CashOutConst.BEFORE_CASH_OUT);
        cashOutEntity.setApplyMoney(form.getApplyMoney());

        //insert into cash out table
        //TODO:need transaction & amount parallel problem
        if(rebateService.addCashOutRecord(cashOutEntity) < 1){
            return Result.createErrorResult(
                    ResultCode.DAO_ERROR, "添加失败");
        }
        userService.modifyAmountById(userEntity.getId(),userEntity.getAmount().subtract(form.getApplyMoney()));
        return Result.createSuccessResult("添加成功");
    }
}
