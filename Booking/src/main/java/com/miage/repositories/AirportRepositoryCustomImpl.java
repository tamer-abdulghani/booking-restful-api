/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.repositories;

import com.miage.models.Airport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author Tamer
 */
public class AirportRepositoryCustomImpl implements AirportRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Airport> find(String nameLike) {
        Query query = new Query();
        query.addCriteria(
                new Criteria()
                        .orOperator(Criteria.where("name").regex(nameLike, "i"), Criteria.where("city").regex(nameLike, "i"))
        );

        List<Airport> airports = mongoTemplate.find(query, Airport.class);
        return airports;
    }

}
