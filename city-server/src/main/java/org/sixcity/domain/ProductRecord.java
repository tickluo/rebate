package org.sixcity.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductRecord extends DataEntity {

    @NotBlank(message = "transId不能为空")
    private Long transId;

    @NotBlank(message = "操作名称不能为空")
    private String operateName;

    @NotBlank(message = "操作类型不能为空")
    private Integer operateType;

    @NotBlank(message = "老状态不能为空")
    private String oldValue;

    @NotBlank(message = "新状态不能为空")
    private String newValue;

    @NotBlank(message = "备注不能为空")
    private String remark;
}
