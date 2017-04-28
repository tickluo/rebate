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
import org.sixcity.domain.User;
import org.sixcity.service.impl.ProductRecordService;
import org.sixcity.service.impl.ProductService;
import org.sixcity.service.impl.TransferService;
import org.sixcity.service.impl.UserService;
import org.sixcity.service.transaction.MoneyChangeTrans;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ApiService implements IApiService {

    private final MoneyChangeTrans moneyChangeTrans;
    private final TransferService transferService;
    private final ProductService productService;
    private final ProductRecordService productRecordService;

    public ApiService(MoneyChangeTrans moneyChangeTrans,
                      TransferService transferService,
                      ProductService productService,
                      ProductRecordService productRecordService) {
        this.moneyChangeTrans = moneyChangeTrans;
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
        //save product
        Product oldProduct = productService.getProductByTransId(transfer.getId());
        if (oldProduct == null) {
            if (productService.addProduct(product) <= 0) {
                throw new DaoException("添加失败");
            }
        } else {
            if (productService.updateProduct(product) <= 0) {
                throw new DaoException("更新失败");
            }
        }

        //build Product entity
        ProductRecord productRecordEntity = new ProductRecord();
        productRecordEntity.setTransId(product.getTransId());
        productRecordEntity.setOperateName("接口");
        productRecordEntity.setOperateType(ProductOperationConst.INSERT);
        productRecordEntity.setOldValue(oldProduct == null ? "" : oldProduct.getProductStatus().toString());
        productRecordEntity.setNewValue(product.getProductStatus().toString());
        productRecordEntity.setRemark(oldProduct == null ?
                "插入数据 商品状态: ".concat(ProductStatusEnum.getText(product.getProductStatus())) :
                "更新数据 商品状态: ".concat(ProductStatusEnum.getText(product.getProductStatus())));
        //insert record
        productRecordService.addProductRecord(productRecordEntity);
    }

    @Transactional(rollbackFor = DaoException.class)
    public void updateProductStatus(Long transId, Integer status) throws ServiceException {

        if (transferService.getTransferByTransId(transId) == null) {
            throw new FindEmptyException("transId 无效");
        }

        Product product = productService.getProductByTransId(transId);
        if (product == null) {
            throw new FindEmptyException("商品不存在");
        }
        // set date according to status
        if (status.equals(ProductStatusEnum.TRANSPORT_IN.getCode())) {
            product.setTransTime(new Date());
        } else if (status.equals(ProductStatusEnum.RECEIVED.getCode())) {
            product.setArrivedTime(new Date());
        } else if (status.equals(ProductStatusEnum.ORDER.getCode())) {
            product.setOrderTime(new Date());
        } else if (status.equals(ProductStatusEnum.CANCELED.getCode())) {
            product.setCancelTime(new Date());
        }

        Integer oldStatus = product.getProductStatus();
        product.setProductStatus(status);
        if (productService.updateProduct(product) <= 0) {
            throw new DaoException("更新失败");
        }

        //cancel product handle(ignore which unsettled)
        if (oldStatus.equals(ProductStatusEnum.CANCELED.getCode()) &&
                status == ProductStatusEnum.CANCELED.getCode() && product.getSettlemented()) {
            moneyChangeTrans.productCancel(product);
        }

        //build Product entity
        ProductRecord productRecordEntity = new ProductRecord();
        productRecordEntity.setTransId(product.getTransId());
        productRecordEntity.setOperateName("接口");
        productRecordEntity.setOperateType(ProductOperationConst.UPDATE_STATUS);
        productRecordEntity.setOldValue(oldStatus.toString());
        productRecordEntity.setNewValue(status.toString());
        String remark = "更新数据 商品状态由: " +
                ProductStatusEnum.getText(oldStatus) +
                " 更新为: " +
                ProductStatusEnum.getText(product.getProductStatus());
        productRecordEntity.setRemark(remark);
        //insert record
        productRecordService.addProductRecord(productRecordEntity);
    }
}
