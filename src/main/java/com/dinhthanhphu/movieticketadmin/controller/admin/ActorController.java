package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.nimbusds.oauth2.sdk.id.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActorController {

    @Autowired
    private IActorService actorService;

    @GetMapping("/edit-actor")
    public ModelAndView editActor(@RequestParam(required = false) String id){
        ModelAndView mav = new ModelAndView("/views/admin/actor/editActor");
        mav.addObject("idol", null);
        if(id != null){
            ActorDTO dto = actorService.findById(id);
            mav.addObject("idol", dto);
        }
        return mav;
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
        mav.addObject("idol", dto);
        return mav;
    }
}
