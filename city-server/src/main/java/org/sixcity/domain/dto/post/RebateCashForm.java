package org.sixcity.domain.dto.post;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RebateCashForm {

    @NotNull(message = "银行Id不能为空")
    private Long bankId;

    @NotNull(message = "申请金额不能为空")
    private BigDecimal applyMoney;
}
