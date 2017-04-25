package org.sixcity.service.serviceimpl;

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
    public List<UserSiteRebatePoints> getUserSitePoint(Long userId) {
        return userSiteRebatePointsMapper.findByUserId(userId);
    }

    /**
     * 查询某一用户的指定网站的返利
     */
    public UserSiteRebatePoints getUserSitePointBySiteId(Long userId, Long siteId) {
        return userSiteRebatePointsMapper.findBySiteId(userId, siteId);
    }

    /**
     * 最终用户的各网站的返利点
     */
    public List<SiteRebatePoints> getFinalUserSitePoint(Long userId) throws ApplicationException {
        List<SiteRebatePoints> siteList = getSiteRebateList();
        List<UserSiteRebatePoints> userSiteList = getUserSitePoint(userId);

        userSiteList.forEach(us -> {
            siteList.stream().filter(s -> Objects.equals(s.getId(), us.getSiteId()))
                    .findAny().get().setSitePoints(us.getSitePoints());
        });

        return siteList;
    }

    /**
     * 最终用户的指定网站的返利点
     */
    public SiteRebatePoints getFinalSitePointByUrl(Long userId, String url) throws ApplicationException {
        List<SiteRebatePoints> siteList = getSiteRebateList();
        Optional<SiteRebatePoints> siteTemp = siteList.stream()
                .filter(site -> url.contains(site.getSiteUrl()))
                .findAny();
        SiteRebatePoints siteRebate = siteTemp.isPresent() ? siteTemp.get() : null;
        if (siteRebate != null) {
            UserSiteRebatePoints userSiteRebate = getUserSitePointBySiteId(userId, siteRebate.getId());
            if (userSiteRebate != null) siteRebate.setSitePoints(userSiteRebate.getSitePoints());
            else siteRebate.setSitePoints(new BigDecimal(0));
        }
        return siteRebate;
    }

    /**
     * 检查用户网站返利点是否已经设置
     */
    public Boolean checkSiteRebatePointsExist(Long userId, Long siteId) {
        return userSiteRebatePointsMapper.checkSiteRebatePointsExist(userId, siteId);
    }

    /**
     * 保存用户网站返利点
     */
    @Transactional(readOnly = false)
    public Boolean saveSiteRebatePoints(UserSiteRebatePoints userSiteRebatePoints) {
        if (checkSiteRebatePointsExist(userSiteRebatePoints.getUserId(), userSiteRebatePoints.getSiteId())) {
            userSiteRebatePoints.preUpdate();
            return userSiteRebatePointsMapper.update(userSiteRebatePoints) > 0;
        }
        userSiteRebatePoints.preInsert();
        return userSiteRebatePointsMapper.insert(userSiteRebatePoints) > 0;
    }
}
