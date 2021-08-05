package com.dinhthanhphu.movieticketadmin.payload.request;

import lombok.Data;

@Data
public class ForgetPasswordRequest {
    private String id;
    private String code;
    private String pass;
}
