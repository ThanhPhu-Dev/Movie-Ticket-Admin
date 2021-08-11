package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.TicketEntity;
import com.dinhthanhphu.movieticketadmin.entity.UserEntity;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor@NoArgsConstructor
@Builder
public class BookingDTO extends BaseDTO{

    private UUID id;
    private Long total;
    private UserEntity user;
    private ShowtimeDTO showtime;
    private List<TicketDTO> ticket;
}
