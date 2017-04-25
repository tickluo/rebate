package org.sixcity.service.impl;

import org.sixcity.domain.ProductRecord;
import org.sixcity.mapper.ProductRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CrudService;

/**
 * 商品状态记录服务实现
 */
@Service
public class ProductRecordService extends CrudService<ProductRecordMapper, ProductRecord> {

    @Transactional(readOnly = false)
    public int addProductRecord(ProductRecord productRecord) {
        productRecord.preInsert();
        return getDao().insert(productRecord);
    }
}
