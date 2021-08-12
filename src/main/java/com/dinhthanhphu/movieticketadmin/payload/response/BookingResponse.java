package com.dinhthanhphu.movieticketadmin.payload.response;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.dto.ShowtimeDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BookingResponse {
    private String cinemaName;
    private String movieName;
    private List<String> seats;
    private Date startDate;
    private Date endDate;
    private Long total;
    private Date createDate;
    private Date ModifyDate;
    private String createBy;
    private String ModifyBy;
    private String id;
}
