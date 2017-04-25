package org.sixcity.api.service.impl;

import exception.DaoException;
import exception.ServiceException;
import org.sixcity.api.exception.FindEmptyException;
import org.sixcity.api.service.IApiService;
import org.sixcity.domain.Product;
import org.sixcity.domain.Transfer;
import org.sixcity.service.impl.ProductService;
import org.sixcity.service.impl.TransferService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ApiService implements IApiService {

    private final TransferService transferService;
    private final ProductService productService;

    public ApiService(TransferService transferService, ProductService productService) {
        this.transferService = transferService;
        this.productService = productService;
    }

    public int saveProduct(Product product) throws ServiceException {

        Transfer transfer = transferService.getTransferByTransId(product.getTransId());
        if (transfer == null) {
            throw new FindEmptyException("transId 无效");
        }
        //build product entity
        product.setTransId(transfer.getId());
        product.setRebateTotalPrice(product.getActuallyPay().multiply(new BigDecimal(product.getQuantity())));

        if (productService.addProduct(product) <= 0) {
            throw new DaoException("添加失败");
        }
        return 1;
    }
}
