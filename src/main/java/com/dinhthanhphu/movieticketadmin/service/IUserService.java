package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.UserDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.RegisterRequest;

import java.util.UUID;

public interface IUserService {
    UserDTO findOneByEmail(String email);
    UserDTO save(UserDTO oAuth2User);
    UserDTO save(RegisterRequest form);
    UserDTO update(UserDTO userDTO);
    UserDTO findOneById(String id);
    UserDTO findOneById(UUID id);
    UserDTO findOneByEmailAndProvider(String email, String provider);
}
