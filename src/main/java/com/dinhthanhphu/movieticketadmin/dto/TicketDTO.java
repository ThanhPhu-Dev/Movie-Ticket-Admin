package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.BookingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.UUID;

@Data
public class TicketDTO extends BaseDTO{

    private UUID id;
    private String seatId;
    private Long price;
    @JsonIgnore
    private BookingEntity booking;
}
