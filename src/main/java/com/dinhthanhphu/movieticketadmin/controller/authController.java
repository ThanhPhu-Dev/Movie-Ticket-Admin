package com.dinhthanhphu.movieticketadmin.controller;

import com.dinhthanhphu.movieticketadmin.dto.UserDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.ForgetPasswordRequest;
import com.dinhthanhphu.movieticketadmin.payload.request.RegisterRequest;
import com.dinhthanhphu.movieticketadmin.service.IUserService;
import com.dinhthanhphu.movieticketadmin.utils.MessageUtils;
import com.dinhthanhphu.movieticketadmin.utils.SendMailUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Controller
public class authController {

//    @Autowired
//    private ServletContext servletContext;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SendMailUtils sendMailUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "views/auth/login";
    }

    @GetMapping("/register")
    public String signup(@RequestParam(value = "alert", required = false) String alert,
                         @RequestParam(value = "message", required = false) String message,
                         Model model) {
        MessageUtils.setMessageToAttribute(alert, message, model);
        return "views/auth/register";
    }



    @GetMapping("/forgetpassword")
    public String forgetPassword(@RequestParam(value = "alert", required = false) String alert,
                                 @RequestParam(value = "message", required = false) String message,
                                 Model model) {
        MessageUtils.setMessageToAttribute(alert, message, model);
        return "views/auth/forgetPassword";
    }

    @GetMapping("/confirmforgetpassword")
    public ModelAndView viewForgetPassword(@RequestParam String id, @RequestParam String code){
        ModelAndView mav = new ModelAndView("views/auth/confirmforgetpassword");
         UserDTO user = userService.findOneById(id);
         if(user != null && Objects.equals(Objects.requireNonNull(user).getCode(),code)){
            mav.addObject("id", user.getId());
            mav.addObject("code", code);
        }else{
            mav.setViewName("redirect:/404");
        }
        return mav;
    }

    @PostMapping ("/confirmforgetpassword")
    public ModelAndView perform_forgetpassword(@ModelAttribute ForgetPasswordRequest form){
        ModelAndView mav = new ModelAndView("views/auth/confirmforgetpassword");
        UserDTO user = userService.findOneById(form.getId());
        if(user != null && Objects.equals(Objects.requireNonNull(user).getCode(),form.getCode())){
            user.setCode(null);
            user.setHasedPassword(passwordEncoder.encode(form.getPass()));
            userService.update(user);
            mav.addObject("alert", "success");
            mav.addObject("message", "Thay đổi thành công");
        }else{
            mav.setViewName("redirect:/404");
        }
        return mav;
    }



    @GetMapping("/registerconfirm")
    public String registerConfirm(@RequestParam String id, @RequestParam String code) {
        String url = null;
        try {
            UserDTO u = userService.findOneById(id);
            if (u != null && Objects.requireNonNull(u).getCode().equals(code)) {
                u.setCode(null);
                u.setActive(true);
                userService.update(u);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        u, null, u.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                url = "redirect:/";
            } else {
                return null;
            }
        } catch (Exception e) {
            url = "redirect:/404";
        }
        return url;
    }

    @PostMapping("/forgetpassword")
    public ModelAndView perform_forgetpassword(@RequestParam(value = "email") String email) {
        ModelAndView mav = new ModelAndView("redirect:/forgetpassword");
        UserDTO user = userService.findOneByEmailAndProvider(email, "Local");
        if (user != null || Objects.requireNonNull(user).isActive()) {
            user.setCode(RandomStringUtils.randomAlphabetic(10));
            userService.update(user);
            try {
                sendMailUtils.sendEmail(email, "Quên mật khẩu", SendMailUtils.mailforgetPassword(user.getId().toString(), user.getCode()));
            } catch (MessagingException | UnsupportedEncodingException e) {
                System.out.println("fail send mail");
                e.printStackTrace();
            }
            mav.addObject("alert", "success");
            mav.addObject("message", "sendMail_success");
        } else {
            mav.addObject("alert", "danger");
            mav.addObject("message", "notFound_Email_Or_NotActive");
        }
        return mav;
    }

    @PostMapping("/register")
    public ModelAndView register( @ModelAttribute RegisterRequest form) {
        ModelAndView mav = new ModelAndView("redirect:/register");
        if (userService.findOneByEmailAndProvider(form.getEmail(), "Local") != null) {
            mav.addObject("alert", "danger");
            mav.addObject("message", "email_exists");
        } else {
            UserDTO u = userService.save(form);
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
