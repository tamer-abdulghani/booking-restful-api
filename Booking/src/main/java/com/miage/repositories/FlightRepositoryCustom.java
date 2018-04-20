/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.repositories;

import com.miage.models.Flight;
import java.util.List;

/**
 *
 * @author Tamer
 */
public interface FlightRepositoryCustom {

    public List<Flight> find(String origin, String destination, String date);
}
