package com.dinhthanhphu.movieticketadmin.config;

import com.dinhthanhphu.movieticketadmin.security.CustomfailureHandler;
import com.dinhthanhphu.movieticketadmin.security.OAuth2LoginSuccessHandler;
import com.dinhthanhphu.movieticketadmin.service.impl.CustomOAuth2UserService;
import com.dinhthanhphu.movieticketadmin.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomOAuth2UserService oauth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomfailureHandler customeFailureHandler() {
        return new CustomfailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/login*", "/css/**", "/js/**", "/register",
                            "/registerconfirm*", "/404*", "/oauth2/**", "/forgetpassword",
                            "/ckeditor/**", "/confirmforgetpassword", "/api/delete-cinema").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("motdepass")
                    .loginProcessingUrl("/j_spring_security_check")
                    .defaultSuccessUrl("/")
                    .failureHandler(customeFailureHandler())
                .and()
                .oauth2Login()
                    .loginPage("/login")
                    .userInfoEndpoint().userService(oauth2UserService)
                    .and()
                    .successHandler(oAuth2LoginSuccessHandler)
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                    .logoutSuccessUrl("/login");
    }
}
