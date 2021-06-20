package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.TheatersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITheatersRepository extends JpaRepository<TheatersEntity, Long> {
}