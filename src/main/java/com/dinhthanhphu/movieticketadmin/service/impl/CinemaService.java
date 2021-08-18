package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.CinemaDTO;
import com.dinhthanhphu.movieticketadmin.entity.CinemaEntity;
import com.dinhthanhphu.movieticketadmin.repository.ICinemaRepository;
import com.dinhthanhphu.movieticketadmin.service.ICinemaService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService implements ICinemaService {

    @Autowired
    private ICinemaRepository cinemaRepository;

    @Autowired
    private MapperModelUtils cvt;

    @Override
    public List<CinemaDTO> findAll() {
        return cinemaRepository.findAll(Sort.by("createDate").descending()).stream().map(m -> cvt.convertToDTO(new CinemaDTO(), m)).collect(Collectors.toList());
    }

    @Override
    public CinemaDTO save(CinemaDTO cinema) {
        CinemaEntity entity = null;
        if(cinema.getId() == null){
            entity = cvt.convertToEntity(cinema, new CinemaEntity());
        }else{
            entity = cinemaRepository.findById(cinema.getId()).orElse(null);
            entity = cvt.convertToEntity(cinema, entity);
        }
        return cvt.convertToDTO(new CinemaDTO(), cinemaRepository.save(entity));
    }

    @Override
    public CinemaDTO findOneById(String id) {
        CinemaEntity entity = cinemaRepository.findById(Long.parseLong(id)).get();
        return cvt.convertToDTO(new CinemaDTO(), entity);
    }

    @Override
    public boolean delete(Long[] ids) {
        try{
            cinemaRepository.deleteAllById(Arrays.asList(ids));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
