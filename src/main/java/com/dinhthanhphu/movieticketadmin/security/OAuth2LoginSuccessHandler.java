package com.dinhthanhphu.movieticketadmin.security;

import com.dinhthanhphu.movieticketadmin.dto.UserDTO;
import com.dinhthanhphu.movieticketadmin.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            UserDTO oAuthUser = (UserDTO) authentication.getPrincipal();
            String email = oAuthUser.getEmail();
            UserDTO user = userService.findOneByEmailAndProvider(email, oAuthUser.getProvider());
            if (user == null) {
                userService.save(oAuthUser);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
