package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.BookingRequest;

import java.util.List;
import java.util.UUID;

public interface IBookingService {
    boolean save(BookingRequest payload);
    List<BookingDTO> findAll();
    boolean delete(List<UUID> ids);
}
