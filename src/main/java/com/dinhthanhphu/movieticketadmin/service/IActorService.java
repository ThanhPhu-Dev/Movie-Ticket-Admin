package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;

import java.util.List;

public interface IActorService {
    ActorDTO save (ActorDTO actorDTO);
    List<ActorDTO> findAll();
    List<ActorDTO> findByNameContaining(String name);
    ActorDTO findById(String id);
    boolean delete(String id);
}
