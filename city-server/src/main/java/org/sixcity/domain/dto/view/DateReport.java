package org.sixcity.domain.dto.view;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DateReport {

    private String orderTime;

    private Long allOrderNum;

    private Long validOrderNum;

    private BigDecimal allOrderPrice;

    private BigDecimal validOrderPrice;

    private BigDecimal allRebatePrice;

    private BigDecimal validRebatePrice;
}
