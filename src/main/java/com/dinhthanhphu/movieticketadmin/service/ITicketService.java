package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.dto.TicketDTO;

import java.util.List;

public interface ITicketService {
    List<TicketDTO> findByShowtimeId(Long id);
}
