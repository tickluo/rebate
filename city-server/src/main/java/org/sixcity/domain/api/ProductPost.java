package org.sixcity.domain.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.sixcity.util.custom.validator.Url;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductPost {

    @NotBlank(message = "transId不能为空")
    private Long transId;

    @NotBlank(message = "商品id不能为空")
    private String itemId;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    @NotBlank(message = "商品Url不能为空")
    @Url(message = "商品Url格式不正确")
    private String url;

    @NotBlank(message = "商品数量不能为空")
    private Integer quantity;

    @NotBlank(message = "商品价格不能为空")
    private BigDecimal price;

    @NotBlank(message = "币种不能为空")
    private String currency;

    @NotBlank(message = "商品实际单价不能为空")
    private BigDecimal actuallyPay;

    @NotBlank(message = "商品状态不能为空")
    private Integer productStatus;

    @NotBlank(message = "下单时间不能为空")
    private Date orderTime;

    private Date transTime;
}
