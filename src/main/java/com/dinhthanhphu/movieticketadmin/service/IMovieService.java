package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.payload.MovieRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMovieService {
    MovieDTO save(MovieRequest form);
    List<MovieDTO> findAll();
    MovieDTO findOneById(String id);
    boolean delete(String id);
    List<MovieDTO> findByNameAndIdCategory(String name, String idCategory);
    List<MovieDTO> findByIdCategory(String idCategory);
    Page<MovieDTO> findPaginated(int pageNo, int pageSize);
}
