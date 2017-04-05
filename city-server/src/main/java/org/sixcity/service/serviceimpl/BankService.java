package org.sixcity.service.serviceimpl;

import org.sixcity.domain.Bank;
import org.sixcity.mapper.BankMapper;
import service.CrudService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 银行相关服务实现
 */
@Service
public class BankService extends CrudService<BankMapper, Bank> {

    @Transactional(readOnly = false)
    public int addBankInfo(Bank bank) {
        bank.preInsert();
        return getDao().insert(bank);
    }

    public boolean checkBankExist(String bankNum) {
        return getDao().checkBankExist(bankNum);
    }

    public List<Bank> findByUserId(Long userId) {
        return getDao().findByUserId(userId);
    }
}
