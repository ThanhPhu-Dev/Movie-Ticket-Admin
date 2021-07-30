package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.CinemaDTO;
import com.dinhthanhphu.movieticketadmin.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CinemaAPI {
    @Autowired
    private ICinemaService cinemaService;

    @GetMapping(value = "/list-cinema")
    public List<CinemaDTO> getAllCinema(){
        List<CinemaDTO> lst = cinemaService.findAll();
        return lst;
    }

    @PostMapping(value = "/add-cinema")
    public CinemaDTO addCinema(@RequestBody CinemaDTO cinema){
        return cinemaService.save(cinema);
    }

    @GetMapping(value = {"/cinema/{id}"})
    public CinemaDTO findOne(@PathVariable String id){
        return cinemaService.findOneById(id);
    }

    @PutMapping(value = "/update-cinema")
    public CinemaDTO updateCinema(@RequestBody CinemaDTO cinema){
        return cinemaService.save(cinema);
    }

    @DeleteMapping(value = "/delete-cinema")
    public boolean deleteCinema(@RequestBody Long[] ids){

        return cinemaService.delete(ids);
    }
}
