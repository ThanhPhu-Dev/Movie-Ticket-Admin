package com.dinhthanhphu.movieticketadmin.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO implements UserDetails, OAuth2User {

    private UUID id;
    private String email;
    private String hasedPassword;
    private String fullname;
    private boolean active;
    private String code;
    private String provider;
    private OAuth2User oauth2User;

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (provider.equals("Local"))
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        else
            return oauth2User.getAuthorities();
    }

    @Override
    public String getPassword() {
        return hasedPassword;
    }

    @Override
    public String getUsername() {
        if (provider.equals("Local"))
            return fullname;
        else
            return oauth2User.getAttribute("name");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }


}
