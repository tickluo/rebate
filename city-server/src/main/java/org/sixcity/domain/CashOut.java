package org.sixcity.domain;

import model.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CashOut extends DataEntity {

    @NotBlank(message = "用户Id不能为空")
    private Long userId;

    @NotBlank(message = "开户人不能为空")
    private String accountName;

    @NotBlank(message = "银行Id不能为空")
    private Long bankId;

    @NotBlank(message = "银行名称不能为空")
    private String bankName;

    @NotBlank(message = "银行账号不能为空")
    private String bankNum;

    @NotBlank(message = "申请金额不能为空")
    private BigDecimal applyMoney;

    @NotBlank(message = "申请时间不能为空")
    private Date applyTime;

    @NotBlank(message = "支付时间不能为空")
    private Date payTime;

}
