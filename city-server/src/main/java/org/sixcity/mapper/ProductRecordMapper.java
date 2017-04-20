package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.sixcity.domain.ProductRecord;

@Mapper
public interface ProductRecordMapper extends CrudDao<ProductRecord> {

    int insert(ProductRecord productRecord);

    int update(ProductRecord productRecord);

}
