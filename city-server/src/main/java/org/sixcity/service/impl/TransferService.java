package org.sixcity.service.impl;

import org.sixcity.domain.Transfer;
import org.sixcity.mapper.TransferMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CrudService;

@Service
public class TransferService extends CrudService<TransferMapper, Transfer> {

    @Transactional(readOnly = false)
    public int addTransfer(Transfer transfer) {

        transfer.preInsert();
        return getDao().insert(transfer);
    }

    public Transfer getTransferByTransId(Long transId) {
        return getDao().findById(transId);
    }

}
