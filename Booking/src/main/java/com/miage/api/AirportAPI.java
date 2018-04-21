/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.api;

import com.miage.models.Airport;
import com.miage.models.exception.APIException;
import com.miage.models.exception.AirportNotFoundException;
import com.miage.models.exception.ExceptionInfo;
import com.miage.repositories.AirportRepository;
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
@RequestMapping("/api/airports")
public class AirportAPI {

    private AirportRepository airportRepository;

    @Autowired
    public AirportAPI(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Airport getAirportById(@PathVariable String id) {
        if (id != null && !"".equals(id)) {
            Optional<Airport> fo = airportRepository.findById(id);
            if (fo.isPresent()) {
                return fo.get();
            }
        }
        throw new AirportNotFoundException(1, "Airport not found");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list/{nameLike}")
    public List<Airport> getAirportsByNameLike(@PathVariable("nameLike") String nameLike) {
        return airportRepository.find(nameLike);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    @ExceptionHandler(AirportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        return new ExceptionInfo(1, "Airport not found", "url");
    }
}
