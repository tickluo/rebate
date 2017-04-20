package org.sixcity.component;

import exception.ApplicationException;
import org.sixcity.domain.CashOut;
import org.sixcity.domain.Product;
import org.sixcity.domain.RebateProduce;
import org.sixcity.domain.User;
import org.sixcity.domain.dto.query.CashRecordQuery;
import org.sixcity.service.serviceimpl.ProductService;
import org.sixcity.service.serviceimpl.RebateProduceService;
import org.sixcity.service.serviceimpl.RebateService;
import org.sixcity.service.serviceimpl.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import util.DateUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class RebateTask {

    private final UserService userService;
    private final RebateService rebateService;
    private final ProductService productService;
    private final RebateProduceService rebateProduceService;

    public RebateTask(UserService userService,
                      RebateService rebateService,
                      ProductService productService,
                      RebateProduceService rebateProduceService) {
        this.userService = userService;
        this.rebateService = rebateService;
        this.productService = productService;
        this.rebateProduceService = rebateProduceService;
    }

    /**
     * 每月1号凌晨1点,结算返利产生
     */
    @Scheduled(cron = "0 0 1 1 * ?")
    public void executeMonthlyRebateTask() {

        Date endDate = DateUtils.getDayBegin(1);
        Date startDate = DateUtils.addMonth(endDate, -1);

        List<User> userList;
        List<RebateProduce> rebateProduceList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        int userCount = 0;
        //normal users
        String userIds = "";
        //abnormal users
        String errorUserIDs = "";

        try {
            //查询上个月没有进行统计的用户集合
            userList = userService.getAllRebatingUser(startDate, endDate);
            userCount = userList.size();

            List<Product> userProductList;
            for (User user : userList) {
                //每个月产生的返利，。判断状态是1.已收货(可结算)，转运中(可结算)  2.没有结算
                userProductList = productService.getUnsettledProduct(user.getId(), startDate, endDate);
                BigDecimal userTotalRebate = new BigDecimal(
                        userProductList
                                .stream()
                                .mapToDouble(i -> i.getRebateTotalPrice().doubleValue()).sum()
                );
                CashRecordQuery condition = new CashRecordQuery();

                condition.setUserId(user.getId());
                condition.setStartTime(startDate);
                condition.setEndTime(endDate);
                List<CashOut> userCashOutList = rebateService.getCashRecordList(condition);
                BigDecimal userTotalCashOut = new BigDecimal(
                        userCashOutList
                                .stream()
                                .mapToDouble(i -> i.getApplyMoney().doubleValue()).sum()
                );

                //set user amount
                user.setAmount(user.getAmount().add(userTotalRebate));
                if (user.getAmount().compareTo(new BigDecimal(0)) < 0) {
                    errorUserIDs += user.getUsername().concat(" | ");
                }

                //set rebateProduce
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(endDate);

                RebateProduce rebateProduce = new RebateProduce();
                rebateProduce.setUserId(user.getId());
                rebateProduce.setApplyMoney(userTotalCashOut);
                rebateProduce.setBalance(user.getAmount());
                rebateProduce.setProduceDate(endDate);
                rebateProduce.setProduceYear(calendar.get(Calendar.YEAR));
                rebateProduce.setRebateMoney(userTotalRebate);

                rebateProduceList.add(rebateProduce);
                userIds += user.getUsername().concat(" | ");

                productList.addAll(userProductList);
            }
            //do rebateProduce
            rebateProduceService.rebateProduce(userList, rebateProduceList, productList);

        } catch (ApplicationException ex) {

        }

    }

}