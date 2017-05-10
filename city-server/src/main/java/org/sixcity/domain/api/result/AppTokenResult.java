package org.sixcity.domain.api.result;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class AppTokenResult {

    @NotBlank(message = "appToken不能为空")
    private String appToken;
}
