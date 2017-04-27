package org.sixcity.mapper;

import model.CrudDao;
import org.apache.ibatis.annotations.Param;
import org.sixcity.domain.UserSiteRebatePoints;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserSiteRebatePointsMapper extends CrudDao<UserSiteRebatePoints> {

    int insert(UserSiteRebatePoints points);

    int update(UserSiteRebatePoints points);

    List<UserSiteRebatePoints> findByAppId(@Param("appId") String appId);

    UserSiteRebatePoints findBySiteId(@Param("appId") String appId, @Param("siteId") Long siteId);

    Boolean checkSiteRebatePointsExist(@Param("appId") String appId, @Param("siteId") Long siteId);

}
