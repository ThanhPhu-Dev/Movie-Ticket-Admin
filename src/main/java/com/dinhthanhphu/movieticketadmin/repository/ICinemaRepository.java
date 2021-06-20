package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICinemaRepository extends JpaRepository<CinemaEntity, Long> {
}