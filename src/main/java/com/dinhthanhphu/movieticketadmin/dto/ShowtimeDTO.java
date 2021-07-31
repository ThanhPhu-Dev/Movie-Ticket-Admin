package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.MovieEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ShowtimeDTO extends BaseDTO{

    private Long id;
    private Date startTime;
    private Date endTime;
    private Float fare;
    private CinemaDTO cinema;
    private MovieDTO movie;
    private Long cinemaId;
    private Long movieId;
}
