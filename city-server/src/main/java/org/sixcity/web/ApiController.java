package org.sixcity.web;

import com.alibaba.fastjson.JSONObject;
import model.Result;
import model.ResultCode;
import org.sixcity.domain.Product;
import org.sixcity.domain.SiteRebatePoints;
import org.sixcity.domain.Transfer;
import org.sixcity.domain.api.ProductPost;
import org.sixcity.domain.api.TransferPost;
import org.sixcity.domain.api.result.TransferResult;
import org.sixcity.security.model.JwtUser;
import org.sixcity.service.serviceimpl.SiteRebatePointsService;
import org.sixcity.service.serviceimpl.TransferService;
import org.sixcity.util.MessageHandleUtils;
import org.sixcity.util.WebUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(
        value = "api/rebate",
        method = RequestMethod.POST
        //produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
        //consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
)
public class ApiController {

    private final SiteRebatePointsService siteRebatePointsService;
    private final TransferService transferService;

    public ApiController(SiteRebatePointsService siteRebatePointsService,
                         TransferService transferService) {
        this.siteRebatePointsService = siteRebatePointsService;
        this.transferService = transferService;
    }

    @RequestMapping(value = "saveProduct")
    public Result saveProduct(@RequestBody @Valid ProductPost productPost,
                              BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }

        //build Product entity
        Product productEntity = JSONObject.parseObject(JSONObject.toJSONString(productPost), Product.class);


        return Result.createSuccessResult();
    }

    @RequestMapping(value = "transferProduct")
    public Result transferProduct(@RequestBody @Valid TransferPost transferPost,
                                  BindingResult bindingResult) {
        // valid params
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return Result.createErrorResult(ResultCode.VALIDATE_ERROR)
                    .setMessage(MessageHandleUtils.getControllerParamsInvalidMessage(fieldErrors));
        }
        JwtUser jwtUser = WebUtils.getCurrentUser();

        SiteRebatePoints siteRebate = siteRebatePointsService.getFinalSitePointByUrl(jwtUser.getId(), transferPost.getUrl());

        //build Transfer entity
        Transfer transfer = new Transfer();
        transfer.setUserId(jwtUser.getId());
        transfer.setTransUrl(transferPost.getUrl());
        transfer.setRebatePoint(siteRebate.getSitePoints());

        if (transferService.addTransfer(transfer) < 1) {
            return Result.createErrorResult(
                    ResultCode.DAO_ERROR, "添加失败");
        }
        TransferResult transferResult = new TransferResult(transfer.getId());
        return Result.createSuccessResult(transferResult, "添加成功");
    }
}
