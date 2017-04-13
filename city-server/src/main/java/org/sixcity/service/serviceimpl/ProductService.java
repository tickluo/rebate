package org.sixcity.service.serviceimpl;

import org.sixcity.domain.Product;
import org.sixcity.domain.dto.query.CpsReportQuery;
import org.sixcity.mapper.ProductsMapper;
import org.springframework.stereotype.Service;
import service.CrudService;

import java.util.List;

/*
 * 商品服务实现
 */
@Service
public class ProductService extends CrudService<ProductsMapper, Product> {

    public List<Product> getProductByQuery(CpsReportQuery condition) {
        return getDao().findList(condition);
    }
}
