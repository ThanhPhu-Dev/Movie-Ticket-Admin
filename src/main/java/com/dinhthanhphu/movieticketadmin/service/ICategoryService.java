package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
}
