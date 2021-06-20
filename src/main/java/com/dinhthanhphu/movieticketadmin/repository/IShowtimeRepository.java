package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.ShowtimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShowtimeRepository extends JpaRepository<ShowtimeEntity, Long> {
}