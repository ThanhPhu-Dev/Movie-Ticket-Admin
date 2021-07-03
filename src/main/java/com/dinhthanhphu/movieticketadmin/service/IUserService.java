package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.UserDTO;

import java.util.UUID;

public interface IUserService {
    UserDTO findOneByEmail(String email);
    UserDTO save(String username, String email, String password);
    UserDTO update(UserDTO userDTO);
    UserDTO findOneById(String id);
    UserDTO findOneById(UUID id);
}
