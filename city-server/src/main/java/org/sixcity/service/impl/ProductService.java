package org.sixcity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.sixcity.constant.state.ProductStatusEnum;
import org.sixcity.domain.Product;
import org.sixcity.domain.dto.query.CpsReportQuery;
import org.sixcity.domain.dto.query.ProductStatusQuery;
import org.sixcity.mapper.ProductsMapper;
import org.springframework.transaction.annotation.Transactional;
import service.CrudService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 商品服务实现
 */
@Service
public class ProductService extends CrudService<ProductsMapper, Product> {

    @Transactional(readOnly = false)
    public int addProduct(Product product) {
        product.preInsert();
        return getDao().insert(product);
    }

    @Transactional(readOnly = false)
    public int updateProduct(Product product) {
        product.preUpdate();
        return getDao().update(product);
    }

    public Product getProductByTransId(Long transId) {
        return getDao().findByTransId(transId);
    }

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

        List<Product> productList = getUnsettledProduct(
                userId,
                startTime == null ? null : formatter.parse(startTime),
                endTime == null ? null : formatter.parse(endTime)
        );
        return new BigDecimal(productList.stream().mapToDouble(i -> i.getRebateTotalPrice().doubleValue()).sum());
    }

    /**
     * 获取未结算商品列表
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Product> getUnsettledProduct(Long userId,
                                             Date startTime,
                                             Date endTime) {

        ProductStatusQuery condition = new ProductStatusQuery();
        if (userId != null) condition.setUserId(userId);
        if (startTime != null) condition.setStartTime(startTime);
        if (endTime != null) condition.setEndTime(endTime);
        List<Integer> statusList = new ArrayList<>();
        statusList.add(ProductStatusEnum.TRANSPORT_IN.getCode());
        statusList.add(ProductStatusEnum.RECEIVED.getCode());
        condition.setProductStatus(statusList);
        return getDao().findListInStatus(condition);
    }

    /**
     * cps报表查询
     *
     * @param condition
     * @return
     */
    public PageInfo<Product> getProductByQuery(CpsReportQuery condition) {
        //do pagination
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        return new PageInfo<>(getDao().findList(condition));
    }

}
