package org.sixcity.domain;

import model.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class Merchant extends DataEntity {

    @NotBlank(message = "appId不能为空")
    private String appId;

    @NotBlank(message = "key不能为空")
    private String appKey;

    @NotBlank(message = "秘钥不能为空")
    private String appSecret;
}
