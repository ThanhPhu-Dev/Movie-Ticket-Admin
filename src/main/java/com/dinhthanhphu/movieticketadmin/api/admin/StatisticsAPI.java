package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.payload.request.StatisticsRequest;
import com.dinhthanhphu.movieticketadmin.payload.response.StatisticsResponse;
import com.dinhthanhphu.movieticketadmin.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticsAPI {

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/statistics-cinema")
    public List<StatisticsResponse> getStatisticsCinema(@RequestBody StatisticsRequest payload){
        return bookingService.statisticsRevenueCinema(payload.getStartDate(), payload.getEndDate());
    }

    @PostMapping("/statistics-movie")
    public List<StatisticsResponse> getStatisticsMovie(@RequestBody StatisticsRequest payload){
        return bookingService.statisticsRevenueMovie(payload.getStartDate(), payload.getEndDate());
    }
}
