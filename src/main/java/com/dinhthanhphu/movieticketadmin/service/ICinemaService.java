package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.CinemaDTO;

import java.util.List;

public interface ICinemaService {
    List<CinemaDTO> findAll();
    CinemaDTO save(CinemaDTO cinema);
    CinemaDTO findOneById(String id);
    boolean delete(Long[] ids);
}
