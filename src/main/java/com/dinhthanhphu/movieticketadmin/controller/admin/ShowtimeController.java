package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.service.ICinemaService;
import com.dinhthanhphu.movieticketadmin.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowtimeController {

    @Autowired
    private ICinemaService cinemaService;

    @Autowired
    private IMovieService movieService;

    @RequestMapping(value = "/list-showtime", method = RequestMethod.GET)
    public String findAll(){
        return "/views/admin/showtime/listShowtime";
    }

    @RequestMapping(value = "/edit-showtime", method = RequestMethod.GET)
    public ModelAndView editShowtime(){
        ModelAndView mav= new ModelAndView("/views/admin/showtime/editShowtime");
        mav.addObject("cinema", cinemaService.findAll());
        mav.addObject("movie", movieService.findAll());
        return mav;
    }
}
