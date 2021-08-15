package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.BookingRequest;
import com.dinhthanhphu.movieticketadmin.payload.response.StatisticsResponse;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IBookingService {
    boolean save(BookingRequest payload);
    List<BookingDTO> findAll();
    boolean delete(List<UUID> ids);
    List<StatisticsResponse> statisticsRevenueCinema (Date start, Date end);
    List<StatisticsResponse> statisticsRevenueMovie (Date start, Date end);
}
