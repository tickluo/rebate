package org.sixcity.service.transaction;

import exception.DaoException;
import org.sixcity.api.exception.FindEmptyException;
import org.sixcity.constant.state.ConsumptionType;
import org.sixcity.domain.ConsumptionRecord;
import org.sixcity.domain.Product;
import org.sixcity.domain.RebateProduce;
import org.sixcity.domain.User;
import org.sixcity.domain.dto.view.MerchantUser;
import org.sixcity.service.impl.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.DateUtils;

import java.util.Date;

@Service
public class MoneyChangeTrans {

    private final UserService userService;
    private final RebateProduceService rebateProduceService;
    private final ConsumptionRecordService consumptionRecordService;

    public MoneyChangeTrans(UserService userService,
                            RebateProduceService rebateProduceService,
                            ConsumptionRecordService consumptionRecordService) {
        this.userService = userService;
        this.rebateProduceService = rebateProduceService;
        this.consumptionRecordService = consumptionRecordService;
    }

    /**
     * 商品状态改为取消后关联操作
     *
     * @param product
     */
    @Transactional(rollbackFor = DaoException.class)
    public void productCancel(Product product) {
        //update user amount
        MerchantUser user = userService.findMerchantUserByAppId(product.getAppId());
        if (user == null) {
            throw new FindEmptyException("用户不存在");
        }
        if (userService.modifyAmountById(
                user.getId(),
                user.getAmount().subtract(product.getRebateTotalPrice())) <= 0) {
            throw new DaoException("用户余额修改失败");
        }
        //update rebateProduce
        Date startDate = product.getSettlementTime();
        Date endDate = DateUtils.addMonth(startDate, 1);
        RebateProduce rebateProduce = rebateProduceService.getRebateProduceByAppIdAndTime(
                product.getAppId(), startDate, endDate
        );
        if (rebateProduce == null) {
            throw new FindEmptyException("返利未产生");
        }
        rebateProduce.setRebateMoney(rebateProduce.getRebateMoney().subtract(product.getRebateTotalPrice()));
        rebateProduce.setBalance(rebateProduce.getBalance().subtract(product.getRebateTotalPrice()));
        if (rebateProduceService.updateRebateProduce(rebateProduce) <= 0) {
            throw new DaoException("产生返利表修改失败");
        }
        //add consumption record
        ConsumptionRecord consumptionRecord = new ConsumptionRecord();
        consumptionRecord.setAppId(product.getAppId());
        consumptionRecord.setTransId(product.getTransId());
        consumptionRecord.setTypeId(ConsumptionType.PRODUCT);
        consumptionRecord.setAmount(product.getRebateTotalPrice().negate());
        consumptionRecord.setNewBalance(user.getAmount());
        consumptionRecord.setRemark("取消商品号:".concat(product.getItemId()));
        consumptionRecordService.addConsumptionRecord(consumptionRecord);
    }
}
