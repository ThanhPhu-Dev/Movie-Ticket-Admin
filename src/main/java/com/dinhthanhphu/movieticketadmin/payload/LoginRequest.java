package com.dinhthanhphu.movieticketadmin.payload;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String motdepass;
}
