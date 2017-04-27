package org.sixcity.service.impl;

import exception.ApplicationException;
import org.sixcity.domain.UserSiteRebatePoints;
import org.sixcity.mapper.UserSiteRebatePointsMapper;

import org.sixcity.domain.SiteRebatePoints;
import org.sixcity.mapper.SiteRebatePointsMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SiteRebatePointsService {

    private final SiteRebatePointsMapper siteRebatePointsMapper;
    private final UserSiteRebatePointsMapper userSiteRebatePointsMapper;

    public SiteRebatePointsService(SiteRebatePointsMapper rebateSitePointsMapper,
                                   UserSiteRebatePointsMapper userSiteRebatePointsMapper) {
        this.siteRebatePointsMapper = rebateSitePointsMapper;
        this.userSiteRebatePointsMapper = userSiteRebatePointsMapper;
    }

    /**
     * 得到各个网站的返利点
     */
    public List<SiteRebatePoints> getSiteRebateList() {
        return siteRebatePointsMapper.findAllList();
    }

    /**
     * 查询某一用户的各网站的返利集合
     */
    public List<UserSiteRebatePoints> getUserSitePoint(String appId) {
        return userSiteRebatePointsMapper.findByAppId(appId);
    }

    /**
     * 查询某一用户的指定网站的返利
     */
    public UserSiteRebatePoints getUserSitePointBySiteId(String appId, Long siteId) {
        return userSiteRebatePointsMapper.findBySiteId(appId, siteId);
    }

    /**
     * 最终用户的各网站的返利点
     */
    public List<SiteRebatePoints> getFinalUserSitePoint(String appId) throws ApplicationException {
        List<SiteRebatePoints> siteList = getSiteRebateList();
        List<UserSiteRebatePoints> userSiteList = getUserSitePoint(appId);

        userSiteList.forEach(us -> {
            siteList.stream().filter(s -> Objects.equals(s.getId(), us.getSiteId()))
                    .findAny().get().setSitePoints(us.getSitePoints());
        });

        return siteList;
    }

    /**
     * 最终用户的指定网站的返利点
     */
    public SiteRebatePoints getFinalSitePointByUrl(String appId, String url) throws ApplicationException {
        List<SiteRebatePoints> siteList = getSiteRebateList();
        Optional<SiteRebatePoints> siteTemp = siteList.stream()
                .filter(site -> url.contains(site.getSiteUrl()))
                .findAny();
        SiteRebatePoints siteRebate = siteTemp.isPresent() ? siteTemp.get() : null;
        if (siteRebate != null) {
            UserSiteRebatePoints userSiteRebate = getUserSitePointBySiteId(appId, siteRebate.getId());
            if (userSiteRebate != null) siteRebate.setSitePoints(userSiteRebate.getSitePoints());
        }
        return siteRebate;
    }

    /**
     * 检查用户网站返利点是否已经设置
     */
    public Boolean checkSiteRebatePointsExist(String appId, Long siteId) {
        return userSiteRebatePointsMapper.checkSiteRebatePointsExist(appId, siteId);
    }

    /**
     * 保存用户网站返利点
     */
    @Transactional(readOnly = false)
    public Boolean saveSiteRebatePoints(UserSiteRebatePoints userSiteRebatePoints) {
        if (checkSiteRebatePointsExist(userSiteRebatePoints.getAppId(), userSiteRebatePoints.getSiteId())) {
            userSiteRebatePoints.preUpdate();
            return userSiteRebatePointsMapper.update(userSiteRebatePoints) > 0;
        }
        userSiteRebatePoints.preInsert();
        return userSiteRebatePointsMapper.insert(userSiteRebatePoints) > 0;
    }
}
