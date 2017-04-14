package org.sixcity.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.sixcity.constant.state.ProductStatusConst;
import org.sixcity.domain.Product;
import org.sixcity.domain.dto.query.CpsReportQuery;
import org.sixcity.domain.dto.query.ProductStatusQuery;
import org.sixcity.mapper.ProductsMapper;
import service.CrudService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * 商品服务实现
 */
@Service
public class ProductService extends CrudService<ProductsMapper, Product> {

    /**
     * 获取用户可申请返利额
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public BigDecimal getUserRebateAmount(Long userId,
                                          String startTime,
                                          String endTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        ProductStatusQuery condition = new ProductStatusQuery();
        if (userId != null) condition.setUserId(userId);
        if (startTime != null && !"".equals(startTime)) condition.setStartTime(formatter.parse(startTime));
        if (endTime != null && !"".equals(endTime)) condition.setEndTime(formatter.parse(endTime));
        List<Integer> statusList = new ArrayList<Integer>();
        statusList.add(ProductStatusConst.TRANSPORT_IN);
        statusList.add(ProductStatusConst.RECEIVED);
        condition.setProductStatus(statusList);
        List<Product> productList = getDao().findListInStatus(condition);
        return new BigDecimal(productList.stream().mapToDouble(i -> i.getRebateTotalPrice().doubleValue()).sum());
    }

    /**
     * cps报表查询
     * @param condition
     * @return
     */
    public PageInfo<Product> getProductByQuery(CpsReportQuery condition) {
        //do pagination
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        return new PageInfo<>(getDao().findList(condition));
    }

}
