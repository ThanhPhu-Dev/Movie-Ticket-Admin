package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.CategoryDTO;
import com.dinhthanhphu.movieticketadmin.repository.ICategoryRepository;
import com.dinhthanhphu.movieticketadmin.service.ICategoryService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;
    
    @Autowired
    private MapperModelUtils cvt;

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map( c -> cvt.convertToDTO(new CategoryDTO(), c)).collect(Collectors.toList());
    }
}
