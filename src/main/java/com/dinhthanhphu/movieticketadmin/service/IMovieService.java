package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.payload.MovieRequest;

import java.util.List;

public interface IMovieService {
    MovieDTO save(MovieRequest form);
    List<MovieDTO> findAll();
    MovieDTO findOneById(String id);
    boolean delete(String id);
    List<MovieDTO> findByNameAndIdCategory(String name, String idCategory);
}
