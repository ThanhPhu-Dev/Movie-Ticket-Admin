package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.TicketEntity;
import com.dinhthanhphu.movieticketadmin.entity.UserEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class BookingDTO extends BaseDTO{

    private UUID id;
    private Date time;
    private Long total;
    private UserEntity user;
    private UserEntity showtime;
    private List<TicketEntity> ticket;
}
