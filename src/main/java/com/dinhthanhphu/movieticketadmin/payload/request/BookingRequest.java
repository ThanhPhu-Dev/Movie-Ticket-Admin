package com.dinhthanhphu.movieticketadmin.payload.request;

import lombok.Data;

@Data
public class BookingRequest {
    private String[] seats;
    private Long total;
    private Long showtimeId;
}
