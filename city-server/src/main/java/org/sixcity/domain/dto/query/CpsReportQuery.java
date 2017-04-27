package org.sixcity.domain.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.Paging;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CpsReportQuery extends Paging {

    private String appId;

    private Integer timeType = 0;

    private String itemId;

    private Integer productStatus;

    private Date startTime;

    private Date endTime;

}
