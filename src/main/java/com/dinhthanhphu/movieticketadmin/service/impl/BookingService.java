package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.dto.ShowtimeDTO;
import com.dinhthanhphu.movieticketadmin.dto.TicketDTO;
import com.dinhthanhphu.movieticketadmin.entity.BookingEntity;
import com.dinhthanhphu.movieticketadmin.entity.ShowtimeEntity;
import com.dinhthanhphu.movieticketadmin.entity.TicketEntity;
import com.dinhthanhphu.movieticketadmin.payload.request.BookingRequest;
import com.dinhthanhphu.movieticketadmin.repository.IBookingRepository;
import com.dinhthanhphu.movieticketadmin.repository.IShowtimeRepository;
import com.dinhthanhphu.movieticketadmin.repository.ITicketRepository;
import com.dinhthanhphu.movieticketadmin.service.IBookingService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IShowtimeRepository showtimeRepository;

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private MapperModelUtils cvt;

    @Override
    @Transactional
    public boolean save(BookingRequest payload) {
        try {
            ShowtimeEntity showtime = showtimeRepository.findById(payload.getShowtimeId()).get();
            BookingEntity booking = new BookingEntity();
            booking.setShowtime(showtime);
            booking.setTotal(payload.getTotal());
            bookingRepository.save(booking);

            TicketEntity ticketEntity;
            for (String seat : payload.getSeats()) {
                ticketEntity = new TicketEntity();
                ticketEntity.setBooking(booking);
                ticketEntity.setPrice(showtime.getFare());
                ticketEntity.setSeatId(seat);
                ticketRepository.save(ticketEntity);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<BookingDTO> findAll() {
        return bookingRepository.findAll()
                .stream().map(b -> cvt.convertToDTO(BookingDTO.builder()
                        .ticket(b.getTicket().stream().map(t -> cvt.convertToDTO(new TicketDTO(), t)).collect(Collectors.toList()))
                        .showtime(cvt.convertToDTO(new ShowtimeDTO(), b.getShowtime())).build(), b)).collect(Collectors.toList());
    }

    @Override
    public boolean delete(List<UUID> ids) {
        try {
            bookingRepository.deleteAllById(ids);
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }


}
