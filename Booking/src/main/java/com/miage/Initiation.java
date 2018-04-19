/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.models.Airport;
import com.miage.models.AirportsList;
import com.miage.repositories.FlightRepository;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tamer
 */
public class Initiation {

    @Autowired
    private FlightRepository repositoryFlight;

    public static void createFlights() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //InputStream is = AirportsList.class.getResourceAsStream("main/resources/data.json");
        //AirportsList a = mapper.readValue(is, AirportsList.class);
        List<Airport> listAirports = mapper.readValue(new File("src/main/resources/airports.json"), new TypeReference<List<Airport>>() {
        });
        //Airport air = mapper.readValue(new File("src/main/resources/airports.json"), Airport.class);
        //System.out.println(""+air.getName());

        for (Airport ab : listAirports) {
            System.out.println("" + ab.getName());
        }
    }

}
