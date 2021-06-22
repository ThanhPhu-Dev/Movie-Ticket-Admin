package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.ImageEntity;
import com.dinhthanhphu.movieticketadmin.entity.ShowtimeEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieDTO extends BaseDTO{

    private Long Id;
    private String name;
    private Integer times;
    private String description;
    private Date openDate;
    private String posterUrl;
    private String posterPublicId;
    private String trailerUrl;
    private String trailerPublicid;
    private List<ShowtimeEntity> showtime;
    private List<ImageEntity> image;
}
