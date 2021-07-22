package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.dto.ImageDTO;
import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.dinhthanhphu.movieticketadmin.service.ICategoryService;
import com.dinhthanhphu.movieticketadmin.service.IImageService;
import com.dinhthanhphu.movieticketadmin.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    private IActorService actorService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IImageService imageService;


    @GetMapping("/edit-movie")
    public ModelAndView editMovie(){
        ModelAndView mav = new ModelAndView("/views/admin/movie/editMovie");
        mav.addObject("lstActor", actorService.findAll());
        mav.addObject("lstCategory", categoryService.findAll());
        return mav;
    }

    @GetMapping("/listMovie")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView("/views/admin/movie/listMovie");
        List<MovieDTO> lst = movieService.findAll();
        mav.addObject("listMovie", lst);
        return mav;
    }

    @GetMapping("/movie/{id}")
    public ModelAndView findMovieById(@PathVariable("id") String id){
        ModelAndView mav = new ModelAndView("/views/admin/movie/detailMovie");
        MovieDTO movie = movieService.findOneById(id);
        mav.addObject("movie", movie);
        return mav;
    }
}
