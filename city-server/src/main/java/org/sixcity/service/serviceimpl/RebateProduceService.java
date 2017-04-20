package org.sixcity.service.serviceimpl;

import org.sixcity.domain.Product;
import org.sixcity.domain.RebateProduce;
import org.sixcity.domain.User;
import org.sixcity.mapper.RebateProduceMapper;
import service.CrudService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RebateProduceService extends CrudService<RebateProduceMapper, RebateProduce> {

    private final UserService userService;
    private final ProductService productService;

    public RebateProduceService(UserService userService,
                                ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    /**
     * 月尾结算
     *
     * @return
     */
    @Transactional(readOnly = false)
    public void rebateProduce(List<User> userList,
                              List<RebateProduce> rebateProduceList,
                              List<Product> productList) {
        //update user amount
        userList.forEach(user -> userService.modifyAmountById(user.getId(), user.getAmount()));
        //add rebateProduce
        rebateProduceList.forEach(rebateProduce -> {
            rebateProduce.preInsert();
            getDao().insert(rebateProduce);
        });
        //update product
        productList.forEach(product -> {
            product.setSettlemented(true);
            product.setSettlementTime(new Date());
            product.setSettlementState(product.getProductStatus());
            productService.updateProduct(product);
        });
    }

}
