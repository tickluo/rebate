package org.sixcity.service.impl;

import com.github.pagehelper.PageHelper;
import org.sixcity.domain.CashOut;
import org.sixcity.domain.dto.query.CashRecordQuery;
import org.sixcity.mapper.RebateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CrudService;

import java.util.List;

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
    public int getUserRebateTimes(String appId) {
        return getDao().getRebateCountByAppId(appId);
    }

    /**
     * 获取提现记录列表
     */
    public List<CashOut> getCashRecordList(CashRecordQuery condition) {
        if (condition.getPageSize() > 0 && condition.getPageNum() >= 0) {
            PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        }
        return getDao().findByAppId(condition);
    }

}
