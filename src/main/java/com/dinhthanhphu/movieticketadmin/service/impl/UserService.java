package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.convert.UserConvert;
import com.dinhthanhphu.movieticketadmin.customException.UserAlreadyExistException;
import com.dinhthanhphu.movieticketadmin.dto.CustomOAuth2User;
import com.dinhthanhphu.movieticketadmin.dto.UserDTO;
import com.dinhthanhphu.movieticketadmin.entity.UserEntity;
import com.dinhthanhphu.movieticketadmin.payload.RegisterRequest;
import com.dinhthanhphu.movieticketadmin.repository.IUserRepository;
import com.dinhthanhphu.movieticketadmin.service.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserConvert usercvt;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, UserAlreadyExistException {
        UserDTO user = usercvt.convertToDTO(userRepository.findOneByEmail(email));
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("emailNotFound");
    }

    public UserDTO findOneByEmail(String email) {
        return usercvt.convertToDTO(userRepository.findOneByEmail(email));
    }



    public UserDTO findOneById(String id) {
        return usercvt.convertToDTO(userRepository.findById(UUID.fromString(id)).get());
    }

    @Override
    public UserDTO findOneById(UUID id) {
        return usercvt.convertToDTO(userRepository.findById(id).get());
    }

    @Override
    public UserDTO findOneByEmailAndProvider(String email, String provider) {
        return usercvt.convertToDTO(userRepository.findByEmailAndProvider(email, provider));
    }

    @Override
    public UserDTO save(CustomOAuth2User oAuth2User) {
        return usercvt.convertToDTO(userRepository.save(
                UserEntity.builder()
                        .email(oAuth2User.getEmail())
                        .fullname(oAuth2User.getName())
                        .hasedPassword(null)
                        .code(null)
                        .provider(oAuth2User.getClientName())
                        .active(true)
                        .build()
        ));
    }

    @Override
    public UserDTO save(RegisterRequest form) {
        return usercvt.convertToDTO(userRepository.save(
                UserEntity.builder()
                        .email(form.getEmail())
                        .fullname(form.getUsername())
                        .hasedPassword(passwordEncoder.encode(form.getPassword()))
                        .code(RandomStringUtils.randomAlphabetic(10))
                        .provider("Local")
                        .active(false)
                        .build()
        ));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return usercvt.convertToDTO(userRepository.findById(userDTO.getId())
                .map(u -> userRepository.save(usercvt.convertToEntity(userDTO, u))).orElse(null));
    }
}
