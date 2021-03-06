package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.MovieRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMovieService {
    MovieDTO save(MovieRequest form);
    List<MovieDTO> findAll();
    MovieDTO findOneById(String id);
    boolean delete(String id);
    List<MovieDTO> findByNameAndIdCategory(String name, String idCategory);
    List<MovieDTO> findByIdCategory(String idCategory);
    Page<MovieDTO> findPaginated(int pageNo, int pageSize);
    Page<MovieDTO> findByNameAndIdCategoryPaginated(int pageNo, int pageSize, String name, String idCategory);
    Page<MovieDTO> findByIdCategoryPaginated(String idCategory, int pageNo, int pageSize);
}
