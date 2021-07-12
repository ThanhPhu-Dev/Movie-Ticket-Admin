package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import com.dinhthanhphu.movieticketadmin.repository.IActorRepository;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
