package com.dinhthanhphu.movieticketadmin.api.admin;

import com.dinhthanhphu.movieticketadmin.dto.ShowtimeDTO;
import com.dinhthanhphu.movieticketadmin.service.IShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShowtimeAPI {

    @Autowired
    private IShowtimeService showtimeService;

    @PostMapping("/edit-showtime")
    public ShowtimeDTO editShowtime(@RequestBody ShowtimeDTO showtime){
        return showtimeService.save(showtime);
    }

    @GetMapping("/list-showtime")
    public List<ShowtimeDTO> getlistShowtime(){
        return showtimeService.findAll();
    }

    @DeleteMapping("/delete-showtime")
    public void deleteShowtime(@RequestBody Long[] ids){
        showtimeService.delete(ids);
    }

    @PutMapping("/edit-showtime")
    public ShowtimeDTO updateShowtime(@RequestBody ShowtimeDTO showtime){
        return showtimeService.save(showtime);
    }
}
