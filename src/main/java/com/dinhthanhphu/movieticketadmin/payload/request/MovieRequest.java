package com.dinhthanhphu.movieticketadmin.payload.request;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MovieRequest {
    private String id;
    private MultipartFile avatar;
    private String name;
    private String time;
    private String openDate;
    private String nation;
    private String[] actor;
    private String[] category;
    private MultipartFile video;
    private String description;
    public MultipartFile[] imageDescription;
    private String[] imageDelete;
}
