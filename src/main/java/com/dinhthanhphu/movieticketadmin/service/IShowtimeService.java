package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.ShowtimeDTO;

import java.util.Date;
import java.util.List;

public interface IShowtimeService {
    ShowtimeDTO save(ShowtimeDTO showtime);
    List<ShowtimeDTO> findAll();
    boolean delete(Long[] ids);
    ShowtimeDTO findById(String id);
    List<ShowtimeDTO> findByStartDateAfter(Date date);
    Integer totalShowtimeOfMonth();
    Integer totalShowtimeOfYear();
}
