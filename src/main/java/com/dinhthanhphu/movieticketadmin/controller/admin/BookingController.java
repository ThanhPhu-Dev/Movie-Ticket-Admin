package com.dinhthanhphu.movieticketadmin.controller.admin;

import com.dinhthanhphu.movieticketadmin.dto.ShowtimeDTO;
import com.dinhthanhphu.movieticketadmin.dto.TicketDTO;
import com.dinhthanhphu.movieticketadmin.service.IBookingService;
import com.dinhthanhphu.movieticketadmin.service.IShowtimeService;
import com.dinhthanhphu.movieticketadmin.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {

    @Autowired
    private IShowtimeService showtimeService;

    @Autowired
    private ITicketService ticketService;

    @RequestMapping(value = "/edit-booking", method = RequestMethod.GET)
    public ModelAndView editBooking(){
        ModelAndView mav= new ModelAndView("views/admin/booking/editBooking");
        return mav;
    }

    @RequestMapping(value = "/list-booking", method = RequestMethod.GET)
    public String listBooking(){
        return "views/admin/booking/listBooking";
    }

    @RequestMapping(value = "/book-seat/{id}", method = RequestMethod.GET)
    public  ModelAndView viewBookSeat(@PathVariable String id){
        ModelAndView mav = new ModelAndView("views/admin/booking/editSeatBook");
        List<TicketDTO> listTicket = ticketService.findByShowtimeId(Long.parseLong(id));
        mav.addObject("showtime", showtimeService.findById(id));
        mav.addObject("seats", listTicket.stream().map(t -> t.getSeatId()).collect(Collectors.toList()));
        return mav;
    }
}
