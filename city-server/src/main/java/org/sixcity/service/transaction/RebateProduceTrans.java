package org.sixcity.service.transaction;

import exception.DaoException;
import org.sixcity.constant.state.ConsumptionType;
import org.sixcity.constant.state.ProductOperationConst;
import org.sixcity.domain.*;
import org.sixcity.service.impl.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RebateProduceTrans {

    private final RebateProduceService rebateProduceService;
    private final UserService userService;
    private final ProductService productService;
    private final ProductRecordService productRecordService;
    private final ConsumptionRecordService consumptionRecordService;

    public RebateProduceTrans(RebateProduceService rebateProduceService,
                              UserService userService,
                              ProductService productService,
                              ProductRecordService productRecordService,
                              ConsumptionRecordService consumptionRecordService) {
        this.rebateProduceService = rebateProduceService;
        this.userService = userService;
        this.productService = productService;
        this.productRecordService = productRecordService;
        this.consumptionRecordService = consumptionRecordService;
    }

    /**
     * 月尾结算
     *
     * @return
     */
    @Transactional(rollbackFor = DaoException.class)
    public void rebateProduce(List<User> userList,
                              List<RebateProduce> rebateProduceList,
                              List<Product> productList) {
        //update user amount
        userList.forEach(user -> userService.modifyAmountById(user.getId(), user.getAmount()));
        //add rebateProduce
        rebateProduceList.forEach(rebateProduce -> {
            rebateProduce.preInsert();
            rebateProduceService.addRebateProduce(rebateProduce);
        });
        //update product
        productList.forEach(product -> {
            User user = userService.findById(product.getUserId());

            product.setSettlemented(true);
            product.setSettlementTime(new Date());
            product.setSettlementState(product.getProductStatus());
            productService.updateProduct(product);
            //insert product record
            ProductRecord productRecord = new ProductRecord();
            productRecord.setTransId(product.getTransId());
            productRecord.setOperateName("系统自动结算");
            productRecord.setOperateType(ProductOperationConst.SIGN_SETTLED);
            productRecord.setNewValue("1");
            productRecord.setOldValue("");
            productRecord.setRemark("标记商品结算, 变为 已结算");
            productRecordService.addProductRecord(productRecord);

            ConsumptionRecord consumptionRecord = new ConsumptionRecord();
            consumptionRecord.setUserId(product.getUserId());
            consumptionRecord.setTransId(product.getTransId());
            consumptionRecord.setTypeId(ConsumptionType.PRODUCT);
            consumptionRecord.setAmount(product.getRebateTotalPrice());
            consumptionRecord.setNewBalance(user.getAmount());
            consumptionRecord.setRemark("结算商品号:".concat(product.getItemId()));
            consumptionRecordService.addConsumptionRecord(consumptionRecord);

        });
    }
}
