package com.dinhthanhphu.movieticketadmin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CinemaController {

    @RequestMapping(value = "/list-cinema", method = RequestMethod.GET)
    public String listCinema(){
        return "/views/admin/cinema/listCinema";
    }
}
