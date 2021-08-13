package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.payload.response.StatisticsResponse;
import com.dinhthanhphu.movieticketadmin.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticsAPI {

    @Autowired
    private IBookingService bookingService;

    @GetMapping("/statistics-cinema")
    public List<StatisticsResponse> getStatisticsCinema(){
        return bookingService.statisticsRevenue();
    }
}
