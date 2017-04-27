package org.sixcity.domain.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.Paging;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CashRecordQuery extends Paging {

    private String appId;

    private Date startTime;

    private Date endTime;
}
