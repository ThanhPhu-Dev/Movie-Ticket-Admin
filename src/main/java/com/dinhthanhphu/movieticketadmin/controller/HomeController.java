package com.dinhthanhphu.movieticketadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "movieAdmin")
public class HomeController {


    @GetMapping("/")
    public String test1(){
        return "index";
    }

    @GetMapping("/test2")
    public String test2(){
        return "views/test2";
    }
}
