package org.sixcity.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import model.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class Bank extends DataEntity {

    @NotBlank(message = "用户Id不能为空")
    private Long userId;

    @NotBlank(message = "银行名称不能为空")
    private String bankNo;

    @NotBlank(message = "银行名称不能为空")
    private String bankName;

    private String bankProvince;

    private String bankCity;

    private String bankDistrict;

    @NotBlank(message = "支行名称不能为空")
    private String bankBranchName;

    @NotBlank(message = "银行账号不能为空")

    private String bankNum;

    @NotBlank(message = "银行账号类型不能为空")
    private Integer bankType;

    @NotBlank(message = "公司账户名称不能为空")
    private String openAccountName;

    @NotBlank(message = "营业执照号不能为空")
    private String licenceNum;

    @NotBlank(message = "营业执照正面不能为空")
    private String licencePositive;

    @NotBlank(message = "营业执照反面不能为空")
    private String licenceSide;
}
