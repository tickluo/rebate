package org.sixcity.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.DataEntity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserSiteRebatePoints extends DataEntity {

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotNull(message = "网站id不能为空")
    private Long siteId;

    @NotNull(message = "网站返点不能为空")
    private BigDecimal sitePoints;

    private Boolean enable = true;
}
