/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.repositories;

import com.miage.models.Booking;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author Tamer
 */
public class BookingRepositoryCustomImpl implements BookingRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Booking> find(Date startDate, Date endDate) {
        Query query = new Query();
        query.addCriteria(
                new Criteria()
                        .andOperator(
                                Criteria.where("bookingDate").gte(startDate).lte(endDate)
                        )
        );

        List<Booking> bookings = mongoTemplate.find(query, Booking.class);
        return bookings;
    }

}
