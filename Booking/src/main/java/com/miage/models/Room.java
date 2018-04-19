/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.models;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author mikhail
 */
@Document(collection = "Rooms")
public class Room {
    @Id
    private String id;
    private RoomType roomType;
    private Integer numberOfPerson;  
    @DBRef
    private Hotel hotel;

    public Room(RoomType roomType, Integer numberOfPerson,Hotel hotel) {
        this.roomType = roomType;
        this.numberOfPerson = numberOfPerson;
        this.hotel=hotel;
    }
    
}
