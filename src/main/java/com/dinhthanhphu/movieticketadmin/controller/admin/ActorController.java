package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.nimbusds.oauth2.sdk.id.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showListActor(Model model){
        return findPaginated(1,model);
    }

    @GetMapping("/actorlist/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize=4;
        Page<ActorDTO> page = actorService.findPaginated(pageNo,pageSize);
        List<ActorDTO> result = page.getContent();
        System.out.println(page.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("lstActor", result);
        return "views/admin/actor/listActor";
    }

    @GetMapping(value = {"/actor/detail/{id}"})
    public ModelAndView DetailActor(@PathVariable String id){
        ModelAndView mav = new ModelAndView("views/admin/actor/detailActor");
        ActorDTO dto = actorService.findById(id);
        mav.addObject("idol", dto);
        return mav;
    }
}
