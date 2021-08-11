package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.BookingRequest;
import com.dinhthanhphu.movieticketadmin.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingAPI {

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/booking")
    public boolean bookingSave(@RequestBody BookingRequest payload){
        return bookingService.save(payload);
    }

    @GetMapping("/list-booking")
    public List<BookingDTO> getListBooking(){
        return bookingService.findAll();
    }
}
