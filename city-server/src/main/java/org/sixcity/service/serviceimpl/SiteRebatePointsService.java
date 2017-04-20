package org.sixcity.service.serviceimpl;

import exception.ApplicationException;
import org.sixcity.domain.UserSiteRebatePoints;
import org.sixcity.mapper.UserSiteRebatePointsMapper;

import org.sixcity.domain.SiteRebatePoints;
import org.sixcity.mapper.SiteRebatePointsMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
     *
     * @param userId
     * @return
     */
    public List<UserSiteRebatePoints> getUserSitePoint(Long userId) {
        return userSiteRebatePointsMapper.findByUserId(userId);
    }

    /**
     * 最终用户的各网站的返利点
     *
     * @param userId
     * @return
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
     * 检查用户网站返利点是否已经设置
     *
     * @param userId
     * @param siteId
     * @return
     */
    public Boolean checkSiteRebatePointsExist(Long userId, Long siteId) {
        return userSiteRebatePointsMapper.checkSiteRebatePointsExist(userId, siteId);
    }

    /**
     * 保存用户网站返利点
     *
     * @param userSiteRebatePoints
     * @return
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
