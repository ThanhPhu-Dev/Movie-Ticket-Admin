package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IBookingRepository extends JpaRepository<BookingEntity, UUID> {

    @Query(value = "SELECT * FROM bookings where showtime_id = ?1", nativeQuery = true)
    List<BookingEntity> findByShowtimeId(Long id);
}