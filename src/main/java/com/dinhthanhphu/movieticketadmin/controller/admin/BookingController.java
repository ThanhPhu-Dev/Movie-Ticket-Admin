package com.dinhthanhphu.movieticketadmin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BookingController {

    @RequestMapping(value = "/edit-booking", method = RequestMethod.GET)
    public String editBooking(){
        return "/views/admin/booking/editBooking";
    }

    @RequestMapping(value = "list-booking", method = RequestMethod.GET)
    public String listBooking(){
        return "/views/admin/booking/listBooking";
    }
}
