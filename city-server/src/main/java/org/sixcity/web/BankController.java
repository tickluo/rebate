package org.sixcity.web;

import exception.ApplicationException;
import model.Result;
import model.ResultCode;
import org.sixcity.domain.Bank;
import org.sixcity.domain.dto.post.BankInfoForm;
import org.sixcity.security.model.JwtUser;
import org.sixcity.service.impl.BankService;
import org.sixcity.service.impl.UploadService;
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

import com.alibaba.fastjson.JSONObject;
import validator.Validator;
import validator.annotation.ValidateParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "bank")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_SUPER_ADMIN')")
public class BankController {

    private final BankService bankService;
    private final UploadService uploadService;

    @Autowired
    public BankController(BankService bankService, UploadService uploadService) {
        this.bankService = bankService;
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "bankInfo", method = RequestMethod.GET)
    public String bankInfo() {
        return "bank/bankInfo";
    }

    @RequestMapping(value = "bankForm", method = RequestMethod.GET)
    public String bankForm() {
        return "bank/bankForm";
    }

    @RequestMapping(value = "getBankList", method = RequestMethod.GET)
    @ResponseBody
    public List<Bank> getBankListByUserId(
            @ValidateParam(name = "每页行数", validators = {Validator.INT}) String rows,
            @ValidateParam(name = "当前页码", validators = {Validator.INT}) String page) {

        JwtUser jwtUser = WebUtils.getCurrentUser();
        return bankService.findByUserId(jwtUser.getId(), rows, page);
    }

    @RequestMapping(value = "getOpenAccountName", method = RequestMethod.GET)
    @ResponseBody
    public Result getOpenAccountNameByUserId() {
        JwtUser jwtUser = WebUtils.getCurrentUser();
        List<Bank> bankList = bankService.findByUserId(jwtUser.getId(), "", "");
        if (bankList.size() < 1) {
            return Result.createErrorResult(ResultCode.SERVICE_ERROR, "该用户还未添加银行卡");
        }
        return Result.createSuccessResult(bankList, "获取成功");
    }

    @RequestMapping(value = "addBankInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result bankFormPost(@RequestBody @Valid BankInfoForm form, BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }
        if (bankService.checkBankExist(form.getBankNum())) {
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage("此银行卡已存在");
        }
        //build Bank Entity
        JwtUser jwtUser = WebUtils.getActualUser();
        Bank bankEntity = JSONObject.parseObject(JSONObject.toJSONString(form), Bank.class);
        bankEntity.setUserId(jwtUser.getId());
        try {
            uploadService.saveImage(bankEntity.getLicencePositive());
            uploadService.saveImage(bankEntity.getLicenceSide());
        } catch (IOException ex) {
            throw new ApplicationException("图片失效");
        }
        //insert
        if (bankService.addBankInfo(bankEntity) <= 0) {
            return Result.createErrorResult(ResultCode.DAO_ERROR)
                    .setMessage("添加失败，请重试");
        }
        return Result.createSuccessResult("添加成功");
    }
}
