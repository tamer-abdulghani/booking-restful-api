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
import com.miage.models.Flight;
import com.miage.repositories.FlightRepository;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tamer
 */
@Component
public class Initiation {

    @Autowired
    private FlightRepository repositoryFlight;

    public void createFlights() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Airport> listAirports = mapper.readValue(new File("src/main/resources/airports.json"), new TypeReference<List<Airport>>() {
        });

        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 180; i++) {
            for (int j = 0; j < 16; j++) {
                LocalDateTime departureTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), j, 00);
                int randomDuration = ThreadLocalRandom.current().nextInt(1, 6 + 1);
                LocalDateTime arrivalTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), j + randomDuration, 00);
                Collections.shuffle(listAirports);
                List<Airport> twoAirports = listAirports.subList(0, 2);
                Airport originAirport = twoAirports.get(0);
                Airport destinationAirport = twoAirports.get(1);

                int flightNumber = ThreadLocalRandom.current().nextInt(100, 600 + 1);
                String flightNumberWithCode = originAirport.getCountry() + flightNumber;

                Flight f = new Flight(
                        originAirport,
                        destinationAirport,
                        departureTime, arrivalTime, flightNumberWithCode, randomDuration);
                repositoryFlight.save(f);
            }
            localDate = localDate.plusDays(1);
            System.out.println("" + localDate);
        }

    }

}
