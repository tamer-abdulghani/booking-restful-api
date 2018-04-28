/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.repositories;

import com.miage.models.Flight;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author Tamer
 */
public class FlightRepositoryCustomImpl implements FlightRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Flight> find(String origin, String destination, String date) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("origin.iata").is(origin),
                        Criteria.where("destination.iata").is(destination)
                )
        );

        List<Flight> flights = mongoTemplate.find(query, Flight.class);
        return flights;
    }

}
