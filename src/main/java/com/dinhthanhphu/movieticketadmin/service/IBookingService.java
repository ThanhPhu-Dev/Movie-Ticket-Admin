package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.BookingRequest;

import java.util.List;

public interface IBookingService {
    boolean save(BookingRequest payload);
    List<BookingDTO> findAll();
}
