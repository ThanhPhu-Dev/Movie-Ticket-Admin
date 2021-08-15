package com.dinhthanhphu.movieticketadmin.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class StatisticsRequest {
    private Date startDate;
    private Date endDate;
}
