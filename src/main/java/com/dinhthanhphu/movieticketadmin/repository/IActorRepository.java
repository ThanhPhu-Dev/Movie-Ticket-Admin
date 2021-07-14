package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IActorRepository extends JpaRepository<ActorEntity, Long> {
    @Query("select a from ActorEntity a where upper(a.name) like upper(concat('%', ?1,'%'))")
    List<ActorEntity> findByNameContaining(String name);

}