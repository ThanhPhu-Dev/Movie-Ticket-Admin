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

   
}
