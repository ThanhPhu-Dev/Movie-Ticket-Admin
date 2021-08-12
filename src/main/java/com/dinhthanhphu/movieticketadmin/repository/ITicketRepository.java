package com.dinhthanhphu.movieticketadmin.repository;

import com.dinhthanhphu.movieticketadmin.entity.BookingEntity;
import com.dinhthanhphu.movieticketadmin.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ITicketRepository extends JpaRepository<TicketEntity, UUID> {
    List<TicketEntity> findByBooking(BookingEntity booking);

    @Query(value = "select * from tickets t join bookings b on t.booking_id = b.id join showtimes s on b.showtime_id = s.id where s.id = 2",
           nativeQuery = true)
    List<TicketEntity> findByShowtimeId(Long showtimeId);
}