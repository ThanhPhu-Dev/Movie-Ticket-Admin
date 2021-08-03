package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.service.ICinemaService;
import com.dinhthanhphu.movieticketadmin.service.IMovieService;
import com.dinhthanhphu.movieticketadmin.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowtimeController {

    @Autowired
    private ICinemaService cinemaService;

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IShowtimeService showtimeService;

    @RequestMapping(value = "/list-showtime", method = RequestMethod.GET)
    public String findAll(){
        return "/views/admin/showtime/listShowtime";
    }

    @RequestMapping(value = {"/edit-showtime", "/edit-showtime/{id}"}, method = RequestMethod.GET)
    public ModelAndView editShowtime(@PathVariable(required = false) String id){
        ModelAndView mav= new ModelAndView("/views/admin/showtime/editShowtime");
        if(id != null){
            mav.addObject("showtime",showtimeService.findById(id));
        }
        mav.addObject("cinema", cinemaService.findAll());
        mav.addObject("movie", movieService.findAll());
        return mav;
    }
}
