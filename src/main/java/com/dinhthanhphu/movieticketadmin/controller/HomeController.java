package com.dinhthanhphu.movieticketadmin.controller;

import com.dinhthanhphu.movieticketadmin.service.IBookingService;
import com.dinhthanhphu.movieticketadmin.service.IShowtimeService;
import com.dinhthanhphu.movieticketadmin.service.ITicketService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller(value = "movieAdmin")
public class HomeController {


    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IShowtimeService showtimeService;

    @Autowired
    private IBookingService bookingService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public ModelAndView home(){

        ModelAndView mav = new ModelAndView("views/index");
        mav.addObject("totalTicketOfMonth", ticketService.totalTicketOfMonth());
        mav.addObject("totalShowtimeOfMonth", showtimeService.totalShowtimeOfMonth());
        mav.addObject("totalTicketOfYear", ticketService.totalTicketOfYear());
        mav.addObject("totalShowtimeOfYear", showtimeService.totalShowtimeOfYear());
        mav.addObject("listMovie", bookingService.statisticsRevenueMovie());
        return mav;
    }


}
