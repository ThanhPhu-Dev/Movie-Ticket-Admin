package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.payload.MovieRequest;
import com.dinhthanhphu.movieticketadmin.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> deleteMovie(@PathVariable("id") String id){

        return null;
    }

}
