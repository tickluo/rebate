package org.sixcity.domain.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.sixcity.util.custom.validator.Url;

@Data
public class TransferPost {

    @NotBlank(message = "key不能为空")
    private String appKey;

    @NotBlank(message = "商品Url不能为空")
    @Url(message = "商品Url格式不正确")
    private String url;

}
