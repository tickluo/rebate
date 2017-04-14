package org.sixcity.web;

import com.github.pagehelper.PageInfo;
import org.sixcity.domain.Product;
import org.sixcity.domain.dto.query.CpsReportQuery;
import org.sixcity.domain.dto.view.DateReport;
import org.sixcity.security.model.JwtUser;
import org.sixcity.service.serviceimpl.ReportService;
import org.sixcity.util.WebUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.StringHelper;
import validator.Validator;
import validator.annotation.ValidateParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public PageInfo<DateReport> dateReportList(
            @ValidateParam(name = "日期类型", validators = {Validator.INT}) String timeType,
            @ValidateParam(name = "开始日期", validators = {Validator.DATE}) String startTime,
            @ValidateParam(name = "结束日期", validators = {Validator.DATE}) String endTime,
            @ValidateParam(name = "每页行数", validators = {Validator.INT}) String rows,
            @ValidateParam(name = "当前页码", validators = {Validator.INT}) String page,
            @ValidateParam(name = "排序") String sort,
            @ValidateParam(name = "排序方向") String sord
    ) throws ParseException {
        JwtUser jwtUser = WebUtils.getCurrentUser();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        //set timeType
        int type = 0;
        if (timeType != null) type = Integer.parseInt(timeType);
        CpsReportQuery condition = new CpsReportQuery();
        condition.setUserId(jwtUser.getId());

        //set Time range
        if (startTime != null && !"".equals(startTime)) condition.setStartTime(formatter.parse(startTime));
        if (endTime != null && !"".equals(endTime)) condition.setEndTime(formatter.parse(endTime));

        //set pagination
        condition.setPageNum(Integer.parseInt(page));
        condition.setPageSize(Integer.parseInt(rows));
        if (sort != null && !"".equals(sort))
            condition.setOrderBy(StringHelper.captureName(sort).concat(" ".concat(sord)));

        //do query
        if (type == 0) return reportService.getALLDateReportList(condition);
        return new PageInfo<DateReport>(reportService.getDateReportList(type, startTime, endTime, jwtUser.getId()));
    }

    @RequestMapping(value = "getCpsReportList", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Product> cpsReportList(
            @ValidateParam(name = "日期类型", validators = {Validator.INT}) String timeType,
            @ValidateParam(name = "开始日期", validators = {Validator.DATE}) String startTime,
            @ValidateParam(name = "结束日期", validators = {Validator.DATE}) String endTime,
            @ValidateParam(name = "商品编号") String itemId,
            @ValidateParam(name = "商品状态", validators = {Validator.INT}) String productStatus,
            @ValidateParam(name = "每页行数", validators = {Validator.INT}) String rows,
            @ValidateParam(name = "当前页码", validators = {Validator.INT}) String page,
            @ValidateParam(name = "排序字段") String sort,
            @ValidateParam(name = "排序方向") String sord
    ) throws ParseException {
        JwtUser jwtUser = WebUtils.getCurrentUser();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        CpsReportQuery condition = new CpsReportQuery();

        //set timeType
        int type = 0;
        if (timeType != null) type = Integer.parseInt(timeType);
        condition.setTimeType(type);

        //set status
        int status = 0;
        if (productStatus != null) status = Integer.parseInt(productStatus);
        condition.setProductStatus(status);

        condition.setItemId(itemId);
        if (startTime != null && !"".equals(startTime)) condition.setStartTime(formatter.parse(startTime));
        if (endTime != null && !"".equals(endTime)) condition.setEndTime(formatter.parse(endTime));

        //valid if superAdmin
        if (jwtUser.getId() != null) condition.setUserId(jwtUser.getId());

        //set pagination
        condition.setPageNum(Integer.parseInt(page));
        condition.setPageSize(Integer.parseInt(rows));
        if (sort != null && !"".equals(sort))
            condition.setOrderBy(StringHelper.captureName(sort).concat(" ".concat(sord)));

        return reportService.getCpsReportList(condition);
    }
}
