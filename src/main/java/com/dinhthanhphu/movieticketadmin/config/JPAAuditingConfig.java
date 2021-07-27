package com.dinhthanhphu.movieticketadmin.config;

import com.dinhthanhphu.movieticketadmin.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null || !authentication.isAuthenticated()) {
                return null;
            }
            if(authentication.getPrincipal().equals("anonymousUser")){
                return Optional.empty();
            }
            UserDTO u =(UserDTO) authentication.getPrincipal();

            return Optional.ofNullable(u.getUsername());
        }
    }
}
