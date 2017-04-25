package org.sixcity.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.DataEntity;
import org.hibernate.validator.constraints.NotBlank;
import org.sixcity.util.custom.validator.Url;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Transfer extends DataEntity {

    @NotBlank(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "商品Url不能为空")
    @Url(message = "商品Url格式不正确")
    private String transUrl;

    @NotBlank(message = "商品价格不能为空")
    private BigDecimal rebatePoint;

}
