package org.sixcity.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConsumptionRecord extends DataEntity {

    @NotBlank(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "transId不能为空")
    private Long transId;

    @NotBlank(message = "类型不能为空")
    private Integer typeId;

    @NotBlank(message = "消费金额不能为空")
    private BigDecimal Amount;

    @NotBlank(message = "余额不能为空")
    private BigDecimal newBalance;

    @NotBlank(message = "备注不能为空")
    private String remark;
}
