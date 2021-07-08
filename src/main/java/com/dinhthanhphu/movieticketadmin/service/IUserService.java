package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.CustomOAuth2User;
import com.dinhthanhphu.movieticketadmin.dto.UserDTO;
import com.dinhthanhphu.movieticketadmin.payload.RegisterRequest;

import java.util.UUID;

public interface IUserService {
    UserDTO findOneByEmail(String email);
    UserDTO save(CustomOAuth2User oAuth2User);
    UserDTO save(RegisterRequest form);
    UserDTO update(UserDTO userDTO);
    UserDTO findOneById(String id);
    UserDTO findOneById(UUID id);
    UserDTO findOneByEmailAndProvider(String email, String provider);
}
