package org.sixcity.service.impl;

import org.sixcity.domain.ConsumptionRecord;
import org.sixcity.mapper.ConsumptionRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CrudService;

/**
 * 消费记录服务实现
 */
@Service
public class ConsumptionRecordService extends CrudService<ConsumptionRecordMapper, ConsumptionRecord> {

    @Transactional(readOnly = false)
    public int addConsumptionRecord(ConsumptionRecord consumptionRecord) {
        consumptionRecord.preInsert();
        return getDao().insert(consumptionRecord);
    }
}
