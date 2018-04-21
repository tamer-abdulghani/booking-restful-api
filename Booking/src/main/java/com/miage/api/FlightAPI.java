/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.api;

import com.miage.models.Flight;
import com.miage.repositories.FlightRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tamer
 */
@RestController
@RequestMapping("/api")
public class FlightAPI {

    private FlightRepository flightRepository;

    @Autowired
    public FlightAPI(FlightRepository customerRepository) {
        this.flightRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights/{origin}/{destination}")
    public List<Flight> getFlightsByOriginDestination(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
        if (origin != null && !"".equals(origin) && destination != null && !"".equals(destination)) {
            return flightRepository.find(origin, destination, null);
        } else {
            return flightRepository.findAll();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights")
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

}
