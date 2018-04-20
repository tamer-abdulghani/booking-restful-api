/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.repositories;


import com.miage.models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author mikhail
 */
public interface RoomRepository extends MongoRepository<Room, String> {

}