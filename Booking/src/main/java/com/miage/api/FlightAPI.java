/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.api;

import com.miage.models.Flight;
import com.miage.models.exception.ExceptionInfo;
import com.miage.models.exception.FlightNotFoundException;
import com.miage.repositories.FlightRepository;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tamer
 */
@RestController
@RequestMapping("/api/flights")
public class FlightAPI {

    private FlightRepository flightRepository;

    @Autowired
    public FlightAPI(FlightRepository customerRepository) {
        this.flightRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Flight getFlightById(@PathVariable String id) {
        if (id != null && !"".equals(id)) {
            Optional<Flight> fo = flightRepository.findById(id);
            if (fo.isPresent()) {
                return fo.get();
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Flight> getFlights(
            @RequestParam(value = "origin", required = true) String origin,
            @RequestParam(value = "destination", required = true) String destination,
            @RequestParam(value = "departuredate", required = true) String departuredate) {
        if (origin != null && !"".equals(origin) && destination != null && !"".equals(destination) && departuredate != null) {
            return flightRepository.find(origin, destination, departuredate);
        } else {
            return flightRepository.findAll();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{origin}/{destination}")
    public List<Flight> getFlightsWithPathVariables(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
        if (origin != null && !"".equals(origin) && destination != null && !"".equals(destination)) {
            return flightRepository.find(origin, destination, null);
        } else {
            return flightRepository.findAll();
        }
    }

    @ExceptionHandler(FlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ExceptionInfo(1, "Booking not found", "url");
    }
}
