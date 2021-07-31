package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.ShowtimeDTO;
import com.dinhthanhphu.movieticketadmin.entity.CinemaEntity;
import com.dinhthanhphu.movieticketadmin.entity.MovieEntity;
import com.dinhthanhphu.movieticketadmin.entity.ShowtimeEntity;
import com.dinhthanhphu.movieticketadmin.repository.ICinemaRepository;
import com.dinhthanhphu.movieticketadmin.repository.IMovieRepository;
import com.dinhthanhphu.movieticketadmin.repository.IShowtimeRepository;
import com.dinhthanhphu.movieticketadmin.service.IShowtimeService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeService implements IShowtimeService {

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private ICinemaRepository cinemaRepository;

    @Autowired
    private IShowtimeRepository showtimeRepository;

    @Autowired
    private MapperModelUtils cvt;

    @Override
    public ShowtimeDTO save(ShowtimeDTO showtime) {

        ShowtimeEntity showtimenew = null;
        if (showtime.getId() != null) {
            showtimenew = showtimeRepository.findById(showtime.getId()).orElse(null);
        }else{
            showtimenew = new ShowtimeEntity();
        }
        MovieEntity movie = movieRepository.findById(showtime.getMovieId()).orElse(null);
        CinemaEntity cinema = cinemaRepository.findById(showtime.getCinemaId()).orElse(null);
        if(movie != null){
            showtimenew.setMovie(movie);
        }
        if(cinema != null){
            showtimenew.setCinema(cinema);
        }

        showtimenew.setStartTime(showtime.getStartTime());
        showtimenew.setEndTime(showtime.getEndTime());
        showtimenew.setFare(showtime.getFare());
        showtimeRepository.save(showtimenew);
        return cvt.convertToDTO(new ShowtimeDTO(), showtimenew);
    }

    @Override
    public List<ShowtimeDTO> findAll() {
        return showtimeRepository.findAll().stream().map(s -> cvt.convertToDTO(new ShowtimeDTO(), s)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean delete(Long[] ids) {
        try{
            showtimeRepository.deleteAllById(Arrays.asList(ids));
        }catch (Exception error){
            return false;
        }
        return true;
    }


}
