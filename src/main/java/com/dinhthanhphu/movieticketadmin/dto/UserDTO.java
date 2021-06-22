package com.dinhthanhphu.movieticketadmin.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO extends BaseDTO{

    private UUID id;
    private String email;
    private String hasedPassword;
    private String fullname;
    private boolean active;
}
