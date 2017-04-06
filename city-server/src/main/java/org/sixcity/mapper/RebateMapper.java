package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.CashOut;

import java.util.List;

@Mapper
public interface RebateMapper extends CrudDao<CashOut> {

    int insert(CashOut cash);

    int Update(CashOut cash);

    List<CashOut> findByUserId(@Param("userId") Long userId);

}
