package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.MovieEntity;
import lombok.Data;


@Data
public class ImageDTO extends BaseDTO{

    private Long id;
    private String publicId;
    private  String publicUrl;
    private MovieDTO movie;
}
