package org.sixcity.domain.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.sixcity.util.custom.validator.Url;

@Data
public class UrlPost {

    @NotBlank(message = "Url不能为空")
    @Url(message = "Url格式不正确")
    private String url;
}
