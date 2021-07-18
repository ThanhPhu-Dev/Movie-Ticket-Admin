package com.dinhthanhphu.movieticketadmin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MovieController {

    @GetMapping("/edit-movie")
    public ModelAndView editMovie(){
        ModelAndView mav = new ModelAndView("/views/admin/movie/editMovie");
        return mav;
    }
}
