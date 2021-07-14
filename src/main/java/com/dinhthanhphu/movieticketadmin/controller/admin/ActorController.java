package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActorController {

    @Autowired
    private IActorService actorService;

    @GetMapping("/edit-actor")
    public String editActor(){
        return "/views/admin/actor/editActor";
    }

    @GetMapping("/actorlist")
    public ModelAndView showListActor(){
        ModelAndView mav = new ModelAndView("views/admin/actor/listActor");
        List<ActorDTO> lst = actorService.findAll();
        mav.addObject("lstActor", lst);
        return mav;
    }

    @GetMapping(value = {"/actor/detail/{id}"})
    public ModelAndView DetailActor(@PathVariable String id){
        ModelAndView mav = new ModelAndView("views/admin/actor/detailACtor");
        ActorDTO dto = actorService.findById(id);
//        dto.getMovies().size()
        mav.addObject("idol", dto);
        return mav;
    }
}
