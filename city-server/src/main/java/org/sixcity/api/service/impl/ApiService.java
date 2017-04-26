package org.sixcity.api.service.impl;

import exception.DaoException;
import exception.ServiceException;
import org.sixcity.api.exception.FindEmptyException;
import org.sixcity.api.service.IApiService;
import org.sixcity.constant.state.ProductOperationConst;
import org.sixcity.constant.state.ProductStatusEnum;
import org.sixcity.domain.Product;
import org.sixcity.domain.ProductRecord;
import org.sixcity.domain.Transfer;
import org.sixcity.service.impl.ProductRecordService;
import org.sixcity.service.impl.ProductService;
import org.sixcity.service.impl.TransferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ApiService implements IApiService {

    private final TransferService transferService;
    private final ProductService productService;
    private final ProductRecordService productRecordService;

    public ApiService(TransferService transferService,
                      ProductService productService,
                      ProductRecordService productRecordService) {
        this.transferService = transferService;
        this.productService = productService;
        this.productRecordService = productRecordService;
    }

    @Transactional(rollbackFor = DaoException.class)
    public void saveProduct(Product product) throws ServiceException {

        Transfer transfer = transferService.getTransferByTransId(product.getTransId());
        if (transfer == null) {
            throw new FindEmptyException("transId 无效");
        }
        //build product entity
        product.setTransId(transfer.getId());
        product.setRebatePoint(transfer.getRebatePoint());
        product.setRebateTotalPrice(product.getActuallyPay().multiply(new BigDecimal(product.getQuantity())));
        product.setSettlementState(product.getProductStatus());

        if (productService.addProduct(product) <= 0) {
            throw new DaoException("添加失败");
        }

        //build Product entity
        ProductRecord productRecordEntity = new ProductRecord();
        productRecordEntity.setTransId(product.getTransId());
        productRecordEntity.setOperateName("接口");
        productRecordEntity.setOperateType(ProductOperationConst.INSERT);
        productRecordEntity.setOldValue("");
        productRecordEntity.setNewValue(product.getProductStatus().toString());
        productRecordEntity.setRemark("插入数据 商品状态: ".concat(ProductStatusEnum.getText(product.getProductStatus())));
        //insert record
        productRecordService.addProductRecord(productRecordEntity);
    }
}
