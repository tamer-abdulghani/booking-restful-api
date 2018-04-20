/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.api;

import com.miage.models.Booking;
import com.miage.models.Traveller;
import com.miage.repositories.BookingRepository;
import com.miage.repositories.TravellerRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tamer
 */
@RestController
@RequestMapping("/api")
public class BookingAPI {

    private BookingRepository bookingRepository;
    private TravellerRepository travellerRepository;

    @Autowired
    public BookingAPI(BookingRepository bookingRepository, TravellerRepository travellerRepository) {
        this.bookingRepository = bookingRepository;
        this.travellerRepository = travellerRepository;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/book")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Booking book(@RequestBody @Valid Booking booking) {
        if (booking.getTravellers().size() == 0 || booking.getFlights().size() == 0) {
            //ToDo: return good message exception 
            return null;
        }

        for (Traveller t : booking.getTravellers()) {
            t = travellerRepository.save(t);
        }

        return bookingRepository.save(booking);
    }
}
