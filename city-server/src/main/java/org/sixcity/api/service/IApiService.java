package org.sixcity.api.service;

import org.sixcity.domain.Product;

public interface IApiService {

    /**
     * 保存商品信息
     *
     * @param product
     * @return
     */
    public void saveProduct(Product product);

    /**
     * 商品状态更改
     * @param transId
     * @param status
     */
    public void updateProductStatus(Long transId, Integer status);

}
