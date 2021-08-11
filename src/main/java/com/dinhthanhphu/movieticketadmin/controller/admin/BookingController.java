package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookingController {

    @Autowired
    private IShowtimeService showtime;

    @RequestMapping(value = "/edit-booking", method = RequestMethod.GET)
    public ModelAndView editBooking(){
        ModelAndView mav= new ModelAndView("views/admin/booking/editBooking");
        return mav;
    }

    @RequestMapping(value = "/list-booking", method = RequestMethod.GET)
    public String listBooking(){
        return "views/admin/booking/listBooking";
    }

    @RequestMapping(value = "/book-seat/{id}")
    public  ModelAndView viewBookSeat(@PathVariable String id){
        ModelAndView mav = new ModelAndView("views/admin/booking/editSeatBook");
        mav.addObject("showtime", showtime.findById(id));
        return mav;
    }
}
