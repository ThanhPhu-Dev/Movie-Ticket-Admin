package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActorRepository extends JpaRepository<ActorEntity, Long> {
}