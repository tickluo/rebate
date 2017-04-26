package org.sixcity.service.impl;

import org.sixcity.domain.*;
import org.sixcity.mapper.RebateProduceMapper;
import service.CrudService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RebateProduceService extends CrudService<RebateProduceMapper, RebateProduce> {

    @Transactional(readOnly = false)
    public int addRebateProduce(RebateProduce rebateProduce) {
        rebateProduce.preInsert();
        return getDao().insert(rebateProduce);
    }

    @Transactional(readOnly = false)
    public int updateRebateProduce(RebateProduce rebateProduce) {
        rebateProduce.preUpdate();
        return getDao().update(rebateProduce);
    }

    public RebateProduce getRebateProduceByUserIdAndTime(Long userId, Date startDate, Date endDate) {
        return getDao().findByUserIdAndTime(userId, startDate, endDate);
    }

}
