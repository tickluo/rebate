package org.sixcity.domain.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class UpdateStatusPost {

    @NotBlank(message = "key不能为空")
    private String appKey;

    @NotNull(message = "transId不能为空")
    private Long transId;

    @NotNull(message = "商品状态不能为空")
    private Integer productStatus;
}
