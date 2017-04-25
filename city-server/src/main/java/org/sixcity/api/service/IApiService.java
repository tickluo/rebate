package org.sixcity.api.service;

import org.sixcity.domain.Product;

public interface IApiService {

    /**
     * 保存商品信息
     * @param product
     * @return
     */
    public int saveProduct(Product product);

}
