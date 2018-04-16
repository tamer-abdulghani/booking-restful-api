/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.booking;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Tamer
 *
 * This repository methods are implemented in the fly automatically. You have to
 * follow the Naming Convention "findBy{PropertyName}"
 */
public interface CustomerRepository extends MongoRepository<Customer, String>, MyCustomerRepository {

    public Customer findByFirstName(String firstName);

    public List<Customer> findByLastName(String lastName);

    public Customer findByPhoneNumber(String string);
}
