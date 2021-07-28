package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.ShowtimeEntity;
import lombok.Data;

import java.util.List;

@Data
public class CinemaDTO extends BaseDTO{

    private Long id;
    private String name;
    private String type;
    private Integer lenght;
    private Integer width;
    private List<ShowtimeEntity> showtime;
}
