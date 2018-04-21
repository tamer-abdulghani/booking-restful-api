/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.api;

import com.miage.models.Airport;
import com.miage.repositories.AirportRepository;
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
public class AirportAPI {

    private AirportRepository airportRepository;

    @Autowired
    public AirportAPI(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/airports/{nameLike}")
    public List<Airport> getAirportsByNameLike(@PathVariable("nameLike") String nameLike) {
        return airportRepository.find(nameLike);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/airports")
    public List<Airport> getAirports(
            @RequestParam(value = "namelike", required = false) String nameLike) {
        return null;
    }

}
