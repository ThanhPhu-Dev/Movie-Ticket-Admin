package com.dinhthanhphu.movieticketadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;

@Controller
public class authController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/login")
    public String login(){
        return "views/auth/login";
    }

    @GetMapping("/register")
    public String signup(){
        return "views/auth/register";
    }

    @GetMapping("/forgetpassword")
    public String forgetPassword(){
        return "views/auth/forgetPassword";
    }

    @PostMapping("/forgetpassword")
    public void register(){

    }
}
