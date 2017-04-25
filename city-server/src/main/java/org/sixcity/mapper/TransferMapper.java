package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.Transfer;

@Mapper
public interface TransferMapper extends CrudDao<Transfer> {

    int insert(Transfer transfer);

    Transfer findById(@Param("transId") Long transId);
}
