package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.BookingEntity;
import lombok.Data;

import java.util.UUID;

@Data
public class TicketDTO extends BaseDTO{

    private UUID id;
    private String seatId;
    private Long price;
    private BookingEntity booking;
}
