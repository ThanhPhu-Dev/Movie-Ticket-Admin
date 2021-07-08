package com.dinhthanhphu.movieticketadmin.payload;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class LoginRequest {
    @Email(message = "email không hợp lệ.")
    private String email;
    private String motdepass;
}
