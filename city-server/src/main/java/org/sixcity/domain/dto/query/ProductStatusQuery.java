package org.sixcity.domain.dto.query;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductStatusQuery {

    private Long userId;

    private List<Integer> productStatus;

    private Date startTime;

    private Date endTime;
}
