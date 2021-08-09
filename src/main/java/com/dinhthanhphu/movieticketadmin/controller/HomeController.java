package com.dinhthanhphu.movieticketadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "movieAdmin")
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }


}
