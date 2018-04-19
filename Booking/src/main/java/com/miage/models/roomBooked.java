/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.models;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 *
 * @author mikhail
 */
public class roomBooked {
    @Id
    private String id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    @DBRef
    private String RoomID;

    public roomBooked(LocalDateTime checkIn, LocalDateTime checkOut, String RoomID) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.RoomID = RoomID;
    }
    
}
