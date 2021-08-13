package com.dinhthanhphu.movieticketadmin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StatisticsController {

    @RequestMapping(value = "/statistics-cinema", method = RequestMethod.GET)
    public String statisticsCinema(){
        return "views/admin/statistics/statisticsCinema";
    }

    @RequestMapping(value = "/statistics-movie", method = RequestMethod.GET)
    public String statisticsMovie(){
        return "views/admin/statistics/statisticsMovie";
    }
}
