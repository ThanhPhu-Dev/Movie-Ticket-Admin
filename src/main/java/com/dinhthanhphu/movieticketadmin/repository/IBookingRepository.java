package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.BookingEntity;
import com.dinhthanhphu.movieticketadmin.payload.response.StatisticsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IBookingRepository extends JpaRepository<BookingEntity, UUID> {

    @Query(value = "SELECT * FROM bookings where showtime_id = ?1", nativeQuery = true)
    List<BookingEntity> findByShowtimeId(Long id);

    @Query(value = "SELECT c.id,c.\"name\",SUM(b.total) FROM cinemas c join showtimes s on c.id = s.cinema_id join bookings b on s.id = b.showtime_id WHERE b.create_date BETWEEN ?1 AND ?2 group BY c.\"name\",c.id",
            nativeQuery = true)
    List<Object[]> revenueCinemaOfTime(Date start, Date end);

    @Query(value = "SELECT m.id, m.\"name\", SUM(b.total) FROM movies m join showtimes s on s.movie_id = m.id join bookings b on s.id = b.showtime_id WHERE b.create_date BETWEEN ?1 AND ?2 GROUP BY m.id, m.\"name\"",
            nativeQuery = true)
    List<Object[]> revenueMovieOfTime(Date start, Date end);

    @Query(value = "SELECT c.id,c.\"name\",SUM(b.total) FROM cinemas c join showtimes s on c.id = s.cinema_id join bookings b on s.id = b.showtime_id group BY c.\"name\",c.id",
            nativeQuery = true)
    List<Object[]> revenueCinema();

    @Query(value = "select m.\"name\", SUM(b.total) from movies m join showtimes s on m.id = s.movie_id join bookings b on b.showtime_id = s.id join tickets t on t.booking_id = b.id WHERE extract(YEAR from b.create_date) = extract(YEAR from NOW()) GROUP BY m.\"name\" LIMIT 10",
            nativeQuery = true)
    List<Object[]> revenueMovieTop10();

    @Query(value = "select Max(date_part('month',create_date)) from bookings", nativeQuery = true)
    Integer getMaxMonthBooking();

    @Query(value = "select date_part('month',create_date), SUM(total) from bookings GROUP BY extract(MONTH from create_date)", nativeQuery = true)
    List<Object[]> revenueBookingByMonth();

    @Query(value = "select extract(quarter  from create_date), SUM(total) from bookings GROUP BY extract(quarter from create_date)", nativeQuery = true)
    List<Object[]> revenueBookingByQuarter();
}