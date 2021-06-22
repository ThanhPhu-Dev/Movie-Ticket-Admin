package com.dinhthanhphu.movieticketadmin.dto;

import com.dinhthanhphu.movieticketadmin.entity.CinemaEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TheaterDTO extends BaseDTO{

    private Long id;
    private String name;
    private String address;
    private List<CinemaEntity> cinema = new ArrayList<>();
}
