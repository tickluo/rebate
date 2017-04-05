package org.sixcity.domain.dto.post;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

@Data
public class RebateCashForm {

    @NotBlank(message = "银行Id不能为空")
    private Long bankId;

    @NotBlank(message = "申请金额不能为空")
    private BigDecimal applyMoney;
}
