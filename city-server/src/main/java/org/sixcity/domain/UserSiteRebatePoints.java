package org.sixcity.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserSiteRebatePoints extends DataEntity {

    @NotBlank(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "网站id不能为空")
    private Long siteId;

    @NotBlank(message = "网站返点不能为空")
    private BigDecimal sitePoints;

    @NotBlank(message = "是否可用不能为空")
    private Boolean enable;
}
