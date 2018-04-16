/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.booking;

import com.mongodb.WriteResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 *
 * @author Tamer
 */
public class CustomerRepositoryImpl implements MyCustomerRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Customer findByPhoneAndFirstName(String firstName, String phone) {

        Query query = new Query();
        //query.addCriteria(Criteria.where("firstName").is(firstName));
        //query.addCriteria(Criteria.where("phone").is(phone));
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("firstName").is(firstName),
                        Criteria.where("phoneNumber").is(phone)
                )
        );

        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        if (customers != null) {
            return customers.get(0);
        } else {
            return null;
        }
    }
}
