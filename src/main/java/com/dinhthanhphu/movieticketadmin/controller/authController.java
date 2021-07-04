package com.dinhthanhphu.movieticketadmin.controller;

import com.dinhthanhphu.movieticketadmin.dto.UserDTO;
import com.dinhthanhphu.movieticketadmin.payload.LoginRequest;
import com.dinhthanhphu.movieticketadmin.payload.RegisterRequest;
import com.dinhthanhphu.movieticketadmin.service.IUserService;
import com.dinhthanhphu.movieticketadmin.utils.MessageUtils;
import com.dinhthanhphu.movieticketadmin.utils.SendMailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Controller
public class authController {

//    @Autowired
//    private ServletContext servletContext;

    @Autowired
    private SendMailUtils sendMailUtils;

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login() {
        return "views/auth/login";
    }

    @PostMapping("/as")
    public String performlogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "views/auth/login";
    }

    @PostMapping("/perform_login")
    public String loginPost() {
        int x= 2;
        return null;
    }

    @GetMapping("/register")
    public String signup(@RequestParam(value = "alert", required = false) String alert,
                         @RequestParam(value = "message", required = false) String message,
                         Model model) {
        MessageUtils.setMessageToAttribute(alert, message, model);
        return "views/auth/register";
    }

    @GetMapping("/registerconfirm")
    public String registerConfirm(@RequestParam String id, @RequestParam String code, Model model) {
        String url = null;
        try {
            UserDTO u = userService.findOneById(id);
            if (u != null) {
                if (u.getCode().equals(code)) {
                    u.setCode(null);
                    u.setActive(true);
                    userService.update(u);
                    url = "views/auth/register";
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            url = "redirect:/404";
        }
        return url;
    }

    @GetMapping("/forgetpassword")
    public String forgetPassword() {
        return "views/auth/forgetPassword";
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute RegisterRequest form) {
        ModelAndView mav = new ModelAndView("redirect:/register");
        if (userService.findOneByEmail(form.getEmail()) != null) {
            mav.addObject("alert", "danger");
            mav.addObject("message", "email_exists");
        } else {
            UserDTO u = userService.save(form.getUsername(), form.getEmail(), form.getPassword());
            if (u != null) {
                try {
                    sendMailUtils.sendEmail(form.getEmail(), "Register Confirm", SendMailUtils.mailRegister(u.getId().toString(), u.getCode()));
                } catch (MessagingException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                mav.addObject("alert", "success");
                mav.addObject("message", "sendMail_success");
            } else {
                mav.addObject("alert", "danger");
                mav.addObject("message", "error_register");
            }
        }
        return mav;
    }
}
