/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.models;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author mikhail
 */
@Document(collection = "hotels")
public class Hotel {

    @Id
    private String id;
    private String adress;
    private String name;
    private Integer postalcode;
    private Integer rate; //how many stars
    private String email;
    private String tel;
    @DBRef
    private ArrayList<Room> rooms;

    public Hotel(String adress, String name, Integer postalcode, Integer rate, String email, String tel) {
        this.adress = adress;
        this.name = name;
        this.postalcode = postalcode;
        this.rate = rate;
        this.email = email;
        this.tel = tel;
        this.rooms = new ArrayList<Room>();
    }

    public String getHotelId() {
        return this.id;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

}
