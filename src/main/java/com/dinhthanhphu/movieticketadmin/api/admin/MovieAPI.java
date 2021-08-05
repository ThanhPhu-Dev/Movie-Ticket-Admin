package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.payload.request.MovieRequest;
import com.dinhthanhphu.movieticketadmin.payload.response.MoviePaginatedResponse;
import com.dinhthanhphu.movieticketadmin.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieAPI {

    @Autowired
    private IMovieService movieService;

    @PostMapping("/edit-movie")
    public ResponseEntity<?> editMovie(@ModelAttribute MovieRequest form) {
        MovieDTO movie = movieService.save(form);
        if (!Optional.ofNullable(movie).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/delete/movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String id){
        if (movieService.delete(id) == true){
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = {"/movie/{idCategory}/{name}", "/movie/{idCategory}"})
    public MoviePaginatedResponse findByNameAndCategoryName(@PathVariable String idCategory,
                                                            @PathVariable(required = false) String name){
        return findByNameAndCategoryNamePaginated(idCategory, name, 1);
    }

    @GetMapping(value = {"/movie/{idCategory}/{name}/page/{pageNo}", "/movie/{idCategory}/page/{pageNo}"})
    public MoviePaginatedResponse findByNameAndCategoryNamePaginated(@PathVariable String idCategory,
                                                                     @PathVariable(required = false) String name,
                                                                     @PathVariable(value = "pageNo") int pageNo
                                                                     ){
        int pageSize = 4;
        Page<MovieDTO> page = movieService.findByNameAndIdCategoryPaginated(pageNo,pageSize,name,idCategory);
        return MoviePaginatedResponse.builder()
                                    .content(page.getContent())
                                    .currentPage(pageNo)
                                    .totalPages(page.getTotalPages())
                                    .totalItems(page.getTotalElements())
                                    .build();
    }

    @GetMapping(value = {"/category-movie/{idCategory}"})
    public List<MovieDTO> findByIdCategory(@PathVariable String idCategory){
        List<MovieDTO> result = movieService.findByIdCategory(idCategory);
        return result;
    }
}
