package org.sixcity.domain.dto.query;

import lombok.Data;

import java.util.Date;

@Data
public class CpsReportQuery {

    private Long userId;

    private Integer timeType = 0;

    private Long itemId;

    private Integer productStatus;

    private Date startTime;

    private Date endTime;
}
