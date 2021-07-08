package com.dinhthanhphu.movieticketadmin.payload;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class RegisterRequest {
    @Email(message = "Email không hợp lệ")
    private String email;
    private String username;
    private String password;
}
