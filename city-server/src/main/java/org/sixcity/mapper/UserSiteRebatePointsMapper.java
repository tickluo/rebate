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

    List<UserSiteRebatePoints> findByUserId(@Param("userId") Long userId);

    Boolean checkSiteRebatePointsExist(@Param("userId") Long userId, @Param("siteId") Long siteId);

}
