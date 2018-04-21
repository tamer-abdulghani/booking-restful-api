/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.repositories;

import com.miage.models.Traveller;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Tamer
 */
public interface TravellerRepository extends MongoRepository<Traveller, String> {

}
