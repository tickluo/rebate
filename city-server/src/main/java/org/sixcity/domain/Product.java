package org.sixcity.domain;

import model.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.sixcity.util.custom.validator.Url;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends DataEntity {

    @NotBlank(message = "商品id不能为空")
    private String itemId;

    @NotNull(message = "transId不能为空")
    private Long transId;

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    @NotBlank(message = "商品Url不能为空")
    @Url(message = "商品Url格式不正确")
    private String url;

    @NotNull(message = "商品数量不能为空")
    private Integer quantity;

    @NotNull(message = "商品价格不能为空")
    private BigDecimal price;

    @NotBlank(message = "币种不能为空")
    private String currency;

    @NotNull(message = "商品实际单价不能为空")
    private BigDecimal actuallyPay;

    @NotNull(message = "返点值不能为空")
    private BigDecimal rebatePoint;

    @NotNull(message = "总的返点值不能为空")
    private BigDecimal rebateTotalPrice;

    @NotBlank(message = "是否结算不能为空")
    private Boolean settlemented = false;

    @NotNull(message = "结算状态不能为空")
    private Integer settlementState;

    private Date settlementTime;

    @NotNull(message = "商品状态不能为空")
    private Integer productStatus;

    @NotBlank(message = "下单时间不能为空")
    private Date orderTime;

    private Date arrivedTime;

    private Date transTime;

    private Date cancelTime;

}
