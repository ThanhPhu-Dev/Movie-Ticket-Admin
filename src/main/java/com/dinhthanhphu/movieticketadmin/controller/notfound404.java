package com.dinhthanhphu.movieticketadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class notfound404 {

    @GetMapping("/404")
    public String login(){
        return "views/404";
    }
}
