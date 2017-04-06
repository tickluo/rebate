package org.sixcity.service.serviceimpl;

import org.sixcity.domain.CashOut;
import org.sixcity.mapper.RebateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CrudService;

@Service
public class RebateService extends CrudService<RebateMapper, CashOut> {

    @Transactional(readOnly = false)
    public int addCashOutRecord(CashOut cashOut) {
        cashOut.preInsert();
        return getDao().insert(cashOut);
    }

    @Transactional(readOnly = false)
    public int updateCashOutRecord(CashOut cashOut) {
        cashOut.preUpdate();
        return getDao().update(cashOut);
    }

    /**
     * 获取用户当月提现次数
     */
    public int getUserRebateTimes(Long userId) {
        return getDao().getRebateCountByUserId(userId);
    }

}
