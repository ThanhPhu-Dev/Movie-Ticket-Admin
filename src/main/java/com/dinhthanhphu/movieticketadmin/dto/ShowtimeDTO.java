package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.MovieEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowtimeDTO extends BaseDTO{

    private Long id;
    private Date startTime;
    private Date endTime;
    private Long fare;
    private CinemaDTO cinema;
    private MovieDTO movie;
    private Long cinemaId;
    private Long movieId;
}
