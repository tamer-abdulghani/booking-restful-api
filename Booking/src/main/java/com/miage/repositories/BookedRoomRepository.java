/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.repositories;


import com.miage.models.roomBooked;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author mikhail
 */
public interface BookedRoomRepository extends MongoRepository<roomBooked, String> {

}

