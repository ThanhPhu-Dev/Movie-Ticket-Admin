package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActorController {

    @GetMapping("/edit-actor")
    public String editActor(){
        return "/views/admin/actor/editActor";
    }


}
