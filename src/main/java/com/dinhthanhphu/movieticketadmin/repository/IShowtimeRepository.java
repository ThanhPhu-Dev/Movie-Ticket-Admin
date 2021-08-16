package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.ShowtimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IShowtimeRepository extends JpaRepository<ShowtimeEntity, Long> {
    List<ShowtimeEntity> findByStartTimeBeforeAndEndTimeAfter(Date date, Date date1);

    @Query(value = "SELECT COUNT(*) from showtimes where extract(MONTH from start_time) = extract(MONTH from NOW())", nativeQuery = true)
    Integer totalShowtimeOfMonth();

    @Query(value = "SELECT COUNT(*) from showtimes where extract(YEAR from start_time) = extract(YEAR from NOW())", nativeQuery = true)
    Integer totalShowtimeOfYear();
}