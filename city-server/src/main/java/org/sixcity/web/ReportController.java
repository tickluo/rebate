package org.sixcity.web;

import org.sixcity.domain.Product;
import org.sixcity.domain.dto.view.DateReport;
import org.sixcity.security.model.JwtUser;
import org.sixcity.service.serviceimpl.ReportService;
import org.sixcity.util.WebUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import validator.Validator;
import validator.annotation.ValidateParam;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping(value = "/report")
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_SUPER_ADMIN')")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "date", method = RequestMethod.GET)
    public String rebateCash() {
        return "report/dateReport";
    }

    @RequestMapping(value = "cps", method = RequestMethod.GET)
    public String cashRecord() {
        return "report/cpsReport";
    }

    @RequestMapping(value = "getDateReportList", method = RequestMethod.GET)
    @ResponseBody
    public List<DateReport> dateReportList(
            @ValidateParam(name = "日期类型", validators = {Validator.INT}) String timeType,
            @ValidateParam(name = "开始日期", validators = {Validator.DATE}) String startTime,
            @ValidateParam(name = "结束日期", validators = {Validator.DATE}) String endTime
    ) throws ParseException {
        JwtUser jwtUser = WebUtils.getCurrentUser();
        int type = 0;
        if (timeType != null) type = Integer.parseInt(timeType);
        if (type == 0) return reportService.getALLDateReportList(startTime, endTime, jwtUser.getId());

        return reportService.getDateReportList(type, startTime, endTime, jwtUser.getId());
    }

    @RequestMapping(value = "getCpsReportList", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> cpsReportList(
            @ValidateParam(name = "日期类型", validators = {Validator.INT}) String timeType,
            @ValidateParam(name = "开始日期", validators = {Validator.DATE}) String startTime,
            @ValidateParam(name = "结束日期", validators = {Validator.DATE}) String endTime,
            @ValidateParam(name = "结束日期") String itemId,
            @ValidateParam(name = "结束日期", validators = {Validator.INT}) String productStatus
    ) throws ParseException {
        JwtUser jwtUser = WebUtils.getCurrentUser();

        return reportService.getCpsReportList(timeType, startTime, endTime, itemId, productStatus, jwtUser.getId());
    }
}
