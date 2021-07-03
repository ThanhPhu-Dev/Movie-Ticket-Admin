package com.dinhthanhphu.movieticketadmin.config;

import com.dinhthanhphu.movieticketadmin.security.CustomfailureHandler;
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
                .antMatchers("/login*", "/css/**", "/js/**", "/register", "/registerconfirm*", "/404*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("motdepass")
                    .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/")
//                .failureUrl("/login?error=true")
                .failureHandler(customeFailureHandler())
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                    .logoutSuccessUrl("/login/html");
    }
}
