package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.dto.TicketDTO;
import com.dinhthanhphu.movieticketadmin.entity.TicketEntity;
import com.dinhthanhphu.movieticketadmin.repository.ITicketRepository;
import com.dinhthanhphu.movieticketadmin.service.ITicketService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private MapperModelUtils cvt;

    @Override
    public List<TicketDTO> findByShowtimeId(Long id) {
        return ticketRepository.findByShowtimeId(id)
                               .stream().map(t -> cvt.convertToDTO(new TicketDTO(), t))
                               .collect(Collectors.toList());
    }

    @Override
    public Integer totalTicketOfYear() {
        return ticketRepository.totalTicketOfYear();
    }

    @Override
    public Integer totalTicketOfMonth() {
        return ticketRepository.totalTicketOfMonth();
    }
}
