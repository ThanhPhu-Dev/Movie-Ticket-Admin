package com.dinhthanhphu.movieticketadmin.service.impl;

import com.dinhthanhphu.movieticketadmin.dto.BookingDTO;
import com.dinhthanhphu.movieticketadmin.dto.ShowtimeDTO;
import com.dinhthanhphu.movieticketadmin.dto.TicketDTO;
import com.dinhthanhphu.movieticketadmin.entity.BookingEntity;
import com.dinhthanhphu.movieticketadmin.entity.ShowtimeEntity;
import com.dinhthanhphu.movieticketadmin.entity.TicketEntity;
import com.dinhthanhphu.movieticketadmin.payload.request.BookingRequest;
import com.dinhthanhphu.movieticketadmin.payload.response.StatisticsResponse;
import com.dinhthanhphu.movieticketadmin.repository.IBookingRepository;
import com.dinhthanhphu.movieticketadmin.repository.IShowtimeRepository;
import com.dinhthanhphu.movieticketadmin.repository.ITicketRepository;
import com.dinhthanhphu.movieticketadmin.service.IBookingService;
import com.dinhthanhphu.movieticketadmin.utils.MapperModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private IShowtimeRepository showtimeRepository;

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private MapperModelUtils cvt;

    @Override
    @Transactional
    public boolean save(BookingRequest payload) {
        try {
            ShowtimeEntity showtime = showtimeRepository.findById(payload.getShowtimeId()).get();
            BookingEntity booking = new BookingEntity();
            booking.setShowtime(showtime);
            booking.setTotal(payload.getTotal());
            bookingRepository.save(booking);

            TicketEntity ticketEntity;
            for (String seat : payload.getSeats()) {
                ticketEntity = new TicketEntity();
                ticketEntity.setBooking(booking);
                ticketEntity.setPrice(showtime.getFare());
                ticketEntity.setSeatId(seat);
                ticketRepository.save(ticketEntity);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<BookingDTO> findAll() {
        return bookingRepository.findAll()
                .stream().map(b -> cvt.convertToDTO(BookingDTO.builder()
                        .ticket(b.getTicket().stream().map(t -> cvt.convertToDTO(new TicketDTO(), t)).collect(Collectors.toList()))
                        .showtime(cvt.convertToDTO(new ShowtimeDTO(), b.getShowtime())).build(), b)).collect(Collectors.toList());
    }

    @Override
    public boolean delete(List<UUID> ids) {
        try {
            bookingRepository.deleteAllById(ids);
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }

    @Override
    public List<StatisticsResponse> statisticsRevenueCinemaOfTime(Date start, Date end) {
        List<Object[]> rs = bookingRepository.revenueCinemaOfTime(start, end);
        List<StatisticsResponse> listRevenue = new ArrayList<>();
        StatisticsResponse revenue = null;
        if (rs != null && !rs.isEmpty()) {
            for (Object[] object : rs) {
                revenue = new StatisticsResponse();
                revenue.setId(((BigInteger) object[0]).longValue());
                revenue.setName((String) object[1]);
                revenue.setTotal(((BigDecimal) object[2]).longValue());
                listRevenue.add(revenue);
            }
        }
        return listRevenue;
    }

    @Override
    public List<StatisticsResponse> statisticsRevenueCinema() {
        List<Object[]> rs = bookingRepository.revenueCinema();
        List<StatisticsResponse> listRevenue = new ArrayList<>();
        StatisticsResponse revenue = null;
        if (rs != null && !rs.isEmpty()) {
            for (Object[] object : rs) {
                revenue = new StatisticsResponse();
                revenue.setId(((BigInteger) object[0]).longValue());
                revenue.setName((String) object[1]);
                revenue.setTotal(((BigDecimal) object[2]).longValue());
                listRevenue.add(revenue);
            }
        }
        return listRevenue;
    }

    @Override
    public List<StatisticsResponse> statisticsRevenueMovieOfTime(Date start, Date end) {
        List<Object[]> rs = bookingRepository.revenueMovieOfTime(start, end);
        List<StatisticsResponse> listRevenueMovie = new ArrayList<>();
        StatisticsResponse revenue = null;
        if (rs != null && !rs.isEmpty()) {
            for (Object[] object : rs) {
                revenue = new StatisticsResponse();
                revenue.setId(((BigInteger) object[0]).longValue());
                revenue.setName((String) object[1]);
                revenue.setTotal(((BigDecimal) object[2]).longValue());
                listRevenueMovie.add(revenue);
            }
        }
        return listRevenueMovie;
    }

    @Override
    public List<StatisticsResponse> statisticsRevenueMovie() {
        List<Object[]> rs = bookingRepository.revenueMovieTop10();
        List<StatisticsResponse> listRevenueMovie = new ArrayList<>();
        StatisticsResponse revenue = null;
        if (rs != null && !rs.isEmpty()) {
            for (Object[] object : rs) {
                revenue = new StatisticsResponse();
                revenue.setName((String) object[0]);
                revenue.setTotal(((BigDecimal) object[1]).longValue());
                listRevenueMovie.add(revenue);
            }
        }
        return listRevenueMovie;
    }

    @Override
    public List<StatisticsResponse> statisticsRevenueBookingByMonth() {
        Integer maxMonth = bookingRepository.getMaxMonthBooking();
        List<Object[]> rs = bookingRepository.revenueBookingByMonth();
        List<StatisticsResponse> listBooking = new ArrayList<>();
        StatisticsResponse sr = null;
        for (int i = 1; i <= maxMonth; i++) {
            sr = new StatisticsResponse();
            sr.setName(String.valueOf(i));
            sr.setTotal(0L);
            for (Object[] object : rs) {
                if (((Double) object[0]).intValue() == i) {
                    sr.setTotal(((BigDecimal) object[1]).longValue());
                }
            }
            listBooking.add(sr);
        }
        return listBooking;
    }

    @Override
    public List<StatisticsResponse> statisticsRevenueBookingByQuarter() {
        List<Object[]> rs = bookingRepository.revenueBookingByQuarter();
        List<StatisticsResponse> listBooking = new ArrayList<>();
        StatisticsResponse sr = null;
        for (int i = 1; i <= 4; i++) {
            sr = new StatisticsResponse();
            sr.setName(String.valueOf(i));
            sr.setTotal(0L);
            for (Object[] object : rs) {
                if (((Double) object[0]).intValue() == i) {
                    sr.setTotal(((BigDecimal) object[1]).longValue());
                }
            }
            listBooking.add(sr);
        }
        return listBooking;
    }
}
