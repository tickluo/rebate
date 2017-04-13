package org.sixcity.domain;

import model.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends DataEntity {

    @NotBlank(message = "商品id不能为空")
    private Long itemId;

    @NotBlank(message = "transId不能为空")
    private String transId;

    @NotBlank(message = "用户id不能为空")
    private Long userId;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    @NotBlank(message = "商品Url不能为空")
    private String url;

    @NotBlank(message = "商品数量不能为空")
    private Integer quantity;

    @NotBlank(message = "商品价格不能为空")
    private BigDecimal price;

    @NotBlank(message = "商品实际单价不能为空")
    private BigDecimal actuallyPay;

    @NotBlank(message = "返点值不能为空")
    private BigDecimal rebatePoint;

    @NotBlank(message = "总的返点值不能为空")
    private BigDecimal rebateTotalPrice;

    @NotBlank(message = "是否结算不能为空")
    private Boolean settlemented;

    @NotBlank(message = "结算状态不能为空")
    private Integer settlementState;

    private Date settlementTime;

    @NotBlank(message = "商品状态不能为空")
    private Integer productStatus;

    @NotBlank(message = "下单时间不能为空")
    private Date orderTime;

    private Date arrivedTime;

    private Date transTime;

    private Date cancelTime;

}
