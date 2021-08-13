package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.BookingEntity;
import com.dinhthanhphu.movieticketadmin.payload.response.StatisticsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IBookingRepository extends JpaRepository<BookingEntity, UUID> {

    @Query(value = "SELECT * FROM bookings where showtime_id = ?1", nativeQuery = true)
    List<BookingEntity> findByShowtimeId(Long id);

    @Query(value = "SELECT c.id,c.\"name\",SUM(b.total) FROM cinemas c join showtimes s on c.id = s.cinema_id join bookings b on s.id = b.showtime_id group BY c.\"name\",c.id",
            nativeQuery = true)
    List<Object[]> revenueCinema();

    @Query(value = "SELECT m.id, m.\"name\", SUM(b.total) FROM movies m join showtimes s on s.movie_id = m.id join bookings b on s.id = b.showtime_id GROUP BY m.id, m.\"name\"",
            nativeQuery = true)
    List<Object[]> revenueMovie();
}