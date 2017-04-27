package org.sixcity.web;

import model.Result;
import model.ResultCode;
import org.sixcity.constant.state.CashOutConst;
import org.sixcity.domain.*;
import org.sixcity.domain.dto.post.RebateCashForm;
import org.sixcity.domain.dto.query.CashRecordQuery;
import org.sixcity.security.model.JwtUser;
import org.sixcity.service.impl.BankService;
import org.sixcity.service.impl.RebateService;
import org.sixcity.service.impl.SiteRebatePointsService;
import org.sixcity.service.impl.UserService;
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
import validator.Validator;
import validator.annotation.ValidateParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(value = "/rebate")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_SUPER_ADMIN')")
public class RebateController {

    private final UserService userService;
    private final BankService bankService;
    private final RebateService rebateService;
    private final SiteRebatePointsService siteRebatePointsService;

    @Autowired
    public RebateController(UserService userService,
                            BankService bankService,
                            RebateService rebateService,
                            SiteRebatePointsService siteRebatePointsService) {
        this.userService = userService;
        this.bankService = bankService;
        this.rebateService = rebateService;
        this.siteRebatePointsService = siteRebatePointsService;
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
        JwtUser jwtUser = WebUtils.getActualUser();

        if ("ROLE_SUPER_ADMIN".equals(WebUtils.getUserAuthority(jwtUser.getAuthorities()))) {
            return "rebate/adminRebateSite";
        }
        return "rebate/rebateSite";
    }

    @RequestMapping(value = "rebateSiteForm", method = RequestMethod.GET)
    public String rebateSiteForm() {
        return "rebate/rebateSiteForm";
    }

    @RequestMapping(value = "getCashRecordList", method = RequestMethod.GET)
    @ResponseBody
    public List<CashOut> cashRecordList(
            @ValidateParam(name = "每页行数", validators = {Validator.INT}) String rows,
            @ValidateParam(name = "当前页码", validators = {Validator.INT}) String page) {
        JwtUser jwtUser = WebUtils.getCurrentUser();
        CashRecordQuery condition = new CashRecordQuery();
        condition.setAppId(jwtUser.getAppId());
        condition.setPageSize(Integer.parseInt(rows));
        condition.setPageNum(Integer.parseInt(page));

        return rebateService.getCashRecordList(condition);
    }

    @RequestMapping(value = "getUserRebateTimes", method = RequestMethod.POST)
    @ResponseBody
    public Result rebateTimes() {
        JwtUser jwtUser = WebUtils.getCurrentUser();
        return Result.createSuccessResult(rebateService.getUserRebateTimes(jwtUser.getAppId()), "提现次数");
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
        JwtUser jwtUser = WebUtils.getActualUser();
        //check user balance
        User userEntity = userService.findById(jwtUser.getId());
        if (userEntity.getAmount().compareTo(form.getApplyMoney()) < 0) {
            return Result.createErrorResult(
                    ResultCode.SERVICE_ERROR, "用户余额不足，余额还剩："
                            .concat(userEntity.getAmount().toString())
            );
        }
        //check jwtUser if bankId valid
        List<Bank> bankList = bankService.findByAppId(jwtUser.getAppId(), "", "");
        Optional<Bank> currentBank = bankList.stream()
                .filter(p -> Objects.equals(p.getId(), form.getBankId()))
                .findAny();
        if (!currentBank.isPresent()) {
            return Result.createErrorResult(
                    ResultCode.SERVICE_ERROR, "银行卡不存在");
        }
        //check user rebate times
        if (rebateService.getUserRebateTimes(jwtUser.getAppId()) >= 10) {
            return Result.createErrorResult(
                    ResultCode.SERVICE_ERROR, "提现次数已经用完");
        }

        //build cash out Entity
        CashOut cashOutEntity = new CashOut();
        cashOutEntity.setAppId(jwtUser.getAppId());
        cashOutEntity.setBankId(form.getBankId());
        cashOutEntity.setAccountName(currentBank.get().getOpenAccountName());
        cashOutEntity.setBankName(currentBank.get().getBankName());
        cashOutEntity.setBankNum(currentBank.get().getBankNum());
        cashOutEntity.setPayTime(new Date(0));
        cashOutEntity.setState(CashOutConst.BEFORE_CASH_OUT);
        cashOutEntity.setApplyMoney(form.getApplyMoney());

        //insert into cash out table
        //TODO:need transaction & amount parallel problem
        if (rebateService.addCashOutRecord(cashOutEntity) < 1) {
            return Result.createErrorResult(
                    ResultCode.DAO_ERROR, "添加失败");
        }
        userService.modifyAmountById(userEntity.getId(), userEntity.getAmount().subtract(form.getApplyMoney()));
        return Result.createSuccessResult("添加成功");
    }

    @RequestMapping(value = "getSiteRebateList", method = RequestMethod.GET)
    @ResponseBody
    public List<SiteRebatePoints> siteRebateList() {
        JwtUser jwtUser = WebUtils.getCurrentUser();
        return siteRebatePointsService.getFinalUserSitePoint(jwtUser.getAppId());
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @RequestMapping(value = "saveSiteRebatePoint", method = RequestMethod.POST)
    @ResponseBody
    public Result siteRebatePointsPost(@RequestBody @Valid UserSiteRebatePoints form, BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }
        if (!siteRebatePointsService.saveSiteRebatePoints(form)) {
            return Result.createErrorResult(ResultCode.DAO_ERROR).setMessage("保存失败");
        }
        return Result.createSuccessResult("添加成功");
    }
}
