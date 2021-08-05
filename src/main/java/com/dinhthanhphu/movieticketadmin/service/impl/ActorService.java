package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.ActorDTO;
import com.dinhthanhphu.movieticketadmin.dto.MovieDTO;
import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import com.dinhthanhphu.movieticketadmin.repository.IActorRepository;
import com.dinhthanhphu.movieticketadmin.repository.IMovieRepository;
import com.dinhthanhphu.movieticketadmin.service.IActorService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ActorService implements IActorService {

    @Autowired
    private MapperModelUtils cvt;

    @Autowired
    private IActorRepository actorRepository;

    @Autowired
    private IMovieRepository movieRepository;

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
        return actorRepository.findByNameContainingCustome(nameidol).stream()
                .map(m -> cvt.convertToDTO(new ActorDTO(), m))
                .collect(Collectors.toList());
    }

    @Override
    public ActorDTO findById(String id) {
        ActorEntity entity = actorRepository.findById(Long.parseLong(id)).orElse(null);
        ActorDTO actor = new ActorDTO();
        actor.setMovies(entity.getMovies().stream().map(m -> cvt.convertToDTO(new MovieDTO(), m)).collect(Collectors.toList()));
        return cvt.convertToDTO(actor, entity);
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

    @Override
    public List<ActorDTO> findByMovie_id(String movie_id) {
        return movieRepository.findById(Long.parseLong(movie_id)).get().getActors()
                .stream().map(m -> cvt.convertToDTO(new ActorDTO(), m)).collect(Collectors.toList());
    }

    @Override
    public Page<ActorDTO> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return actorRepository.findAll(pageable).map(m  -> cvt.convertToDTO(new ActorDTO(), m));
//        List<ActorDTO> lstActor  = actorRepository.findAll()
//                                    .stream().map( m -> cvt.convertToDTO(new ActorDTO(), m)).collect(Collectors.toList());
//        int start = (int)pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), lstActor.size());
//        Page<ActorDTO> rs =  new PageImpl<ActorDTO>(lstActor.subList(start,end), pageable, lstActor.size());
//        System.out.println(rs.getSize());
//        System.out.println(rs.getTotalPages());
    }

    @Override
    public Page<ActorDTO> findByNameContainingPaginated(String name, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize);
        String nameidol = "";
        if(name != null){
            nameidol = name;
        }
        return actorRepository.findByNameContainingPaginated(nameidol, pageable)
                .map(m -> cvt.convertToDTO(new ActorDTO(), m));
    }
}
