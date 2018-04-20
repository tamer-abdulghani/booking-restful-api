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
public class FlightAPI {

    private FlightRepository flightRepository;

    @Autowired
    public FlightAPI(FlightRepository customerRepository) {
        this.flightRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/flights")
    public List<Flight> getFlights(@PathVariable String origin, @PathVariable String destination, @PathVariable String departuredate) {
        //List<Flight> flights = flightRepository.find(origin, destination, departuredate);
        return null;
    }

}
