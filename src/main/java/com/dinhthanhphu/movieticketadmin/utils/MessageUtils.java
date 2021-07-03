package com.dinhthanhphu.movieticketadmin.utils;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class MessageUtils {

    public static void setMessageToAttribute(String alert, String message, Model model){
        try {
            ResourceBundle mybundle = ResourceBundle.getBundle("message");
            if(message != null) {
                if(alert != null){
                    model.addAttribute("alert", alert);
                }
                String value = mybundle.getString(message);
                model.addAttribute("message", new String(value.getBytes("ISO-8859-1"), "UTF-8"));
            }
        }catch (UnsupportedEncodingException e){
            return;
        }
    }


}
