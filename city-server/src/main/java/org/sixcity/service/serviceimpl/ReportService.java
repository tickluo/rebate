package org.sixcity.service.serviceimpl;

import org.sixcity.constant.state.ProductStatusConst;
import org.sixcity.domain.Product;
import org.sixcity.domain.dto.query.CpsReportQuery;
import org.sixcity.domain.dto.view.DateReport;
import org.sixcity.mapper.ProductsMapper;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     * @param startTime
     * @param endTime
     * @param userId
     * @return
     * @throws ParseException
     */
    public List<DateReport> getALLDateReportList(String startTime,
                                                 String endTime,
                                                 Long userId) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (startTime == null || "".equals(startTime)) startTime = "0000-00-00";
        if (endTime == null || "".equals(endTime)) endTime = "0000-00-00";
        CpsReportQuery cpsReportQuery = new CpsReportQuery();
        cpsReportQuery.setUserId(userId);
        cpsReportQuery.setStartTime(formatter.parse(startTime));
        cpsReportQuery.setEndTime(formatter.parse(endTime));
        List<Product> reportList = productService.getProductByQuery(cpsReportQuery);

        return reportList.stream().map(r -> {
            boolean flag = !r.getProductStatus().equals(ProductStatusConst.WAIT_PAYS)
                    && !r.getProductStatus().equals(ProductStatusConst.CANCELED);
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
        }).collect(Collectors.toList());
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
        if ("".equals(startTime)) startTime = "0000-00-00";
        if ("".equals(endTime)) endTime = "0000-00-00";
        return productsMapper.getDateReportList(timeType, userId, startTime, endTime);
    }
}
