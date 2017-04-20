package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.sixcity.domain.ConsumptionRecord;

@Mapper
public interface ConsumptionRecordMapper extends CrudDao<ConsumptionRecord>{

    int insert(ConsumptionRecord consumptionRecord);

    int update(ConsumptionRecord consumptionRecord);
}
