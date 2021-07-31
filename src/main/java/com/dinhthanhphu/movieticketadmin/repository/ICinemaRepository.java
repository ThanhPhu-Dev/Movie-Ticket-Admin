package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICinemaRepository extends JpaRepository<CinemaEntity, Long> {
    @Query("select a from CinemaEntity a where upper(a.name) like upper(concat('%', ?1,'%'))")
    List<CinemaEntity> findByNameContainingCustom(String name);
}