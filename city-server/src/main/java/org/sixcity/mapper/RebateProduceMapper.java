package org.sixcity.mapper;

import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.RebateProduce;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface RebateProduceMapper extends CrudDao<RebateProduce> {

    int insert(RebateProduce rebateProduce);

    int update(RebateProduce rebateProduce);

    RebateProduce findByUserIdAndTime(@Param("userId") Long userId,
                                      @Param("startTime") Date startTime,
                                      @Param("endTime") Date endTime);
}
