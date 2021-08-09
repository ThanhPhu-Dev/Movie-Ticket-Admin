package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.dto.CategoryDTO;
import com.dinhthanhphu.movieticketadmin.dto.ImageDTO;
import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.dinhthanhphu.movieticketadmin.service.ICategoryService;
import com.dinhthanhphu.movieticketadmin.service.IImageService;
import com.dinhthanhphu.movieticketadmin.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @GetMapping(value = {"/edit-movie", "/edit-movie/{id}"})
    public ModelAndView editMovie(@PathVariable(required = false) String id){
        ModelAndView mav = new ModelAndView("views/admin/movie/editMovie");
        if(id != null){
            MovieDTO movie = movieService.findOneById(id);
            mav.addObject("movie", movie);
        }
        mav.addObject("lstActor", actorService.findAll());
        mav.addObject("lstCategory", categoryService.findAll());
        return mav;
    }

    @GetMapping(value = {"/edit-movie-test", "/edit-movie-test/{id}"})
    public ModelAndView testmovie(@PathVariable(required = false) String id){
        ModelAndView mav = new ModelAndView("views/admin/movie/editMovie");
        return mav;
    }

    @GetMapping("/listMovie")
    public String findAll(Model model){
        return findPaginated(1,model);
    }

    @GetMapping("/listMovie/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 4;
        Page<MovieDTO> page = movieService.findPaginated(pageNo,pageSize);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMovie", page.getContent());
        model.addAttribute("lstCategory", categoryService.findAll());
        return "views/admin/movie/listMovie";
    }

    @GetMapping("/movie/{id}")
    public ModelAndView findMovieById(@PathVariable("id") String id){
        ModelAndView mav = new ModelAndView("views/admin/movie/detailMovie");
        MovieDTO movie = movieService.findOneById(id);
        mav.addObject("movie", movie);
        return mav;
    }
}
