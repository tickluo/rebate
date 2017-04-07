package org.sixcity.domain;

import lombok.EqualsAndHashCode;
import model.DataEntity;

import org.hibernate.validator.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;

@EqualsAndHashCode(callSuper = true)
@Data
public class SiteRebatePoints extends DataEntity {

    @NotBlank(message = "网站名称不能为空")
    private String siteName;

    @NotBlank(message = "网站域名不能为空")
    private String siteUrl;

    @NotBlank(message = "网站返点不能为空")
    private BigDecimal sitePoints;
}
