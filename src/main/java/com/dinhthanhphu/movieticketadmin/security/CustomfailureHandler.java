package com.dinhthanhphu.movieticketadmin.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomfailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl("/login?error=true");
        String errorMessage= null;
        super.onAuthenticationFailure(request, response, exception);
        if(exception.getMessage().equalsIgnoreCase("emailNotActive")){
            errorMessage = "Email chưa được kích hoạt";
        }else if(exception.getMessage().equalsIgnoreCase("emailNotFound")){
            errorMessage = "Email Không tìm thấy";
        }
        request.getSession().setAttribute("message", errorMessage);
    }
}
