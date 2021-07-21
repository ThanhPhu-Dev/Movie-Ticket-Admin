package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.CategoryDTO;
import com.dinhthanhphu.movieticketadmin.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/allCategory")
    public List<CategoryDTO> findAll(){
        return categoryService.findAll();
    }
}
