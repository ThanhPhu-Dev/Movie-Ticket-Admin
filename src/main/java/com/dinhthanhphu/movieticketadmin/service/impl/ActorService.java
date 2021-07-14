package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import com.dinhthanhphu.movieticketadmin.repository.IActorRepository;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ActorService implements IActorService {

    @Autowired
    private MapperModelUtils cvt;

    @Autowired
    private IActorRepository actorRepository;

    @Override
    public ActorDTO save(ActorDTO actorDTO) {
        ActorEntity entity = actorRepository.save(cvt.convertToEntity(actorDTO, new ActorEntity()));
        return cvt.convertToDTO(new ActorDTO(), entity);
    }

    @Override
    public List<ActorDTO> findAll() {
        return actorRepository.findAll().stream()
                .map(s -> cvt.convertToDTO(new ActorDTO(), s))
                .collect(Collectors.toList());
    }

    @Override
    public List<ActorDTO> findByNameContaining(String name) {
        String nameidol = "";
        if(name != null){
            nameidol = name;
        }
        return actorRepository.findByNameContaining(nameidol).stream()
                .map(m -> cvt.convertToDTO(new ActorDTO(), m))
                .collect(Collectors.toList());
    }

    @Override
    public ActorDTO findById(String id) {
        ActorEntity entity = actorRepository.findById(Long.parseLong(id)).orElse(null);
        return cvt.convertToDTO(new ActorDTO(), entity);
    }

    @Override
    public boolean delete(String id) {
        try {
            ActorEntity entity = actorRepository.findById(Long.parseLong(id))
                    .orElseThrow(Exception::new);
            actorRepository.delete(entity);
        }catch (Exception error){
            return false;
        }
        return true;
    }
}
