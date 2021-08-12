package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.dto.ShowtimeDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.BookingRequest;
import com.dinhthanhphu.movieticketadmin.payload.response.BookingResponse;
import com.dinhthanhphu.movieticketadmin.service.IBookingService;
import com.dinhthanhphu.movieticketadmin.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookingAPI {

    @Autowired
    private IBookingService bookingService;

    @Autowired
    private IShowtimeService showtimeService;

    @PostMapping("/booking")
    public boolean bookingSave(@RequestBody BookingRequest payload) {
        return bookingService.save(payload);
    }

    @GetMapping("/list-booking")
    public List<BookingResponse> getListBooking() {
        List<BookingDTO> list = bookingService.findAll();
        List<BookingResponse> rs = new ArrayList<>();
        BookingResponse brp = null;
        ShowtimeDTO st = null;
        for (BookingDTO br : list) {
            brp = new BookingResponse();
            st = showtimeService.findById(br.getShowtime().getId().toString());
            brp.setCreateBy(br.getCreateBy());
            brp.setModifyBy(br.getModifyBy());
            brp.setTotal(br.getTotal());
            brp.setId(br.getId().toString());
            brp.setCreateDate(br.getCreateDate());
            brp.setModifyDate(br.getModifyDate());
            brp.setCinemaName(st.getCinema().getName());
            brp.setMovieName(st.getMovie().getName());
            brp.setStartDate(st.getStartTime());
            brp.setEndDate(st.getEndTime());
            brp.setSeats(br.getTicket().stream().map(s -> s.getSeatId()).collect(Collectors.toList()));
            rs.add(brp);
        }
        return rs;
    }
}
