package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.CashOut;
import org.sixcity.domain.dto.query.CashRecordQuery;

import java.util.List;

@Mapper
public interface RebateMapper extends CrudDao<CashOut> {

    int insert(CashOut cash);

    int Update(CashOut cash);

    int getRebateCountByUserId(@Param("userId") Long userId);

    List<CashOut> findByUserId(@Param("condition") CashRecordQuery condition);

}
