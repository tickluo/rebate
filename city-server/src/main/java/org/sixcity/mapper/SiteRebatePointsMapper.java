package org.sixcity.mapper;

import model.CrudDao;
import org.sixcity.domain.SiteRebatePoints;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SiteRebatePointsMapper extends CrudDao<SiteRebatePoints> {

    int insert(SiteRebatePoints points);

    int update(SiteRebatePoints points);

    List<SiteRebatePoints> findAllList();

}
