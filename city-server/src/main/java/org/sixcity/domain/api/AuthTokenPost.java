package org.sixcity.domain.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AuthTokenPost {

    @NotBlank(message = "appKey不能为空")
    private String appKey;

    @NotBlank(message = "签名不能为空")
    private String signature;
}
