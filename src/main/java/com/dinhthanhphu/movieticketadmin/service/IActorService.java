package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;

import java.util.List;

public interface IActorService {
    ActorDTO save (ActorDTO actorDTO);
    List<ActorDTO> findAll();
    List<ActorDTO> findByNameContaining(String name);
    ActorDTO findById(String id);
    boolean delete(String id);
    List<ActorDTO> findByMovie_id(String movie_id);
}
