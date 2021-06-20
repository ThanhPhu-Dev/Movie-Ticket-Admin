package com.dinhthanhphu.movieticketadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class authController {

    @GetMapping("/login")
    public String index(){
        return "views/auth/login";
    }
}
