package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.Product;

import org.apache.ibatis.annotations.Mapper;
import org.sixcity.domain.dto.query.CpsReportQuery;
import org.sixcity.domain.dto.query.ProductStatusQuery;
import org.sixcity.domain.dto.view.DateReport;

import java.util.List;

@Mapper
public interface ProductsMapper extends CrudDao<Product> {

    int insert(Product product);

    int update(Product product);

    Product findByTransId(Long transId);

    List<Product> findListInStatus(@Param("condition") ProductStatusQuery condition);

    List<Product> findList(@Param("condition") CpsReportQuery condition);

    List<DateReport> getDateReportList(@Param("timeType") int timeType,
                                       @Param("userId") Long userId,
                                       @Param("startTime") String startTime,
                                       @Param("endTime") String endTime);
}
