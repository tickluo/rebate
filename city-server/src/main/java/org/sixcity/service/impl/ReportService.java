package org.sixcity.service.impl;

import com.github.pagehelper.PageInfo;
import org.sixcity.constant.state.ProductStatusEnum;
import org.sixcity.domain.Product;
import org.sixcity.domain.dto.query.CpsReportQuery;
import org.sixcity.domain.dto.view.DateReport;
import org.sixcity.mapper.ProductsMapper;

import org.springframework.stereotype.Service;
import util.StringHelper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 报表服务实现
 */
@Service
public class ReportService {

    private final ProductService productService;
    private final ProductsMapper productsMapper;

    public ReportService(ProductService productService, ProductsMapper productsMapper) {
        this.productService = productService;
        this.productsMapper = productsMapper;
    }

    /**
     * 查询全部日期列表
     *
     * @param cpsReportQuery
     * @return
     */
    public PageInfo<DateReport> getALLDateReportList(CpsReportQuery cpsReportQuery) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        PageInfo<Product> reportList = productService.getProductByQuery(cpsReportQuery);
        PageInfo<DateReport> resultList = new PageInfo<>(reportList.getList().stream().map(r -> {
            boolean flag = !r.getProductStatus().equals(ProductStatusEnum.WAIT_PAYS.getCode())
                    && !r.getProductStatus().equals(ProductStatusEnum.CANCELED.getCode());
            DateReport dr = new DateReport();
            dr.setOrderTime(formatter.format(r.getOrderTime()));
            dr.setAllOrderNum(1L);
            dr.setValidOrderNum(flag ? 1L : 0);
            BigDecimal allOrderPrice = r.getActuallyPay().multiply(new BigDecimal(r.getQuantity()));
            dr.setAllOrderPrice(allOrderPrice);
            dr.setValidOrderPrice(flag ? allOrderPrice : new BigDecimal(0));
            dr.setAllRebatePrice(r.getRebateTotalPrice());
            dr.setValidRebatePrice(flag ? r.getRebateTotalPrice() : new BigDecimal(0));
            return dr;
        }).collect(Collectors.toList()));
        resultList.setPageSize(reportList.getPageSize());
        resultList.setPageNum(reportList.getPageNum());
        resultList.setEndRow(reportList.getEndRow());
        resultList.setPages(reportList.getPages());
        resultList.setSize(reportList.getSize());
        resultList.setTotal(reportList.getTotal());
        return resultList;
    }

    /**
     * 根据日期类型查询日期列表
     *
     * @param timeType
     * @param startTime
     * @param endTime
     * @param userId
     * @return
     */
    public List<DateReport> getDateReportList(int timeType,
                                              String startTime,
                                              String endTime,
                                              Long userId) {
        if (!StringHelper.isNotBlank(startTime)) startTime = "0000-00-00";
        if (!StringHelper.isNotBlank(endTime)) endTime = "0000-00-00";
        return productsMapper.getDateReportList(timeType, userId, startTime, endTime);
    }

    public PageInfo<Product> getCpsReportList(CpsReportQuery condition) throws ParseException {

        return productService.getProductByQuery(condition);
    }
}
