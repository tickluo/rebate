package org.sixcity.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class RebateProduce extends DataEntity {

    @NotBlank(message = "商户Id不能为空")
    private String appId;

    @NotBlank(message = "年份不能为空")
    private int produceYear;

    @NotBlank(message = "产生时间不能为空")
    private Date produceDate;

    @NotBlank(message = "产生返利不能为空")
    private BigDecimal rebateMoney;

    @NotBlank(message = "提现返利不能为空")
    private BigDecimal applyMoney;

    @NotBlank(message = "剩下余额不能为空")
    private BigDecimal balance;
}
