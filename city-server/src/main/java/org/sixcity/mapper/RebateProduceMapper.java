package org.sixcity.mapper;

import org.sixcity.domain.RebateProduce;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RebateProduceMapper extends CrudDao<RebateProduce> {

    int insert(RebateProduce rebateProduce);
}
