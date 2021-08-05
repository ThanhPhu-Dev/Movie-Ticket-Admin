package com.dinhthanhphu.movieticketadmin.service;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IActorService {
    ActorDTO save (ActorDTO actorDTO);
    List<ActorDTO> findAll();
    List<ActorDTO> findByNameContaining(String name);
    ActorDTO findById(String id);
    boolean delete(String id);
    List<ActorDTO> findByMovie_id(String movie_id);
    Page<ActorDTO> findPaginated(int pageNo, int pageSize);
    Page<ActorDTO> findByNameContainingPaginated(String name, int pageNo, int pageSize);
}
