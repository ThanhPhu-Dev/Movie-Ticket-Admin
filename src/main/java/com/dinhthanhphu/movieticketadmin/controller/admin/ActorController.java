package com.dinhthanhphu.movieticketadmin.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActorController {

    @GetMapping("/edit-actor")
    public String editActor(){
        return "/views/admin/actor/editActor";
    }
}
