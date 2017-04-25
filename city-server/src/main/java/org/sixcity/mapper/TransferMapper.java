package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.sixcity.domain.Transfer;

@Mapper
public interface TransferMapper extends CrudDao<Transfer> {

    int insert(Transfer transfer);
}
