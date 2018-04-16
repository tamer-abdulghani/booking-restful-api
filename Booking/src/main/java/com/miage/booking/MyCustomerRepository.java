/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.booking;

/**
 *
 * @author Tamer
 */
public interface MyCustomerRepository {

    public Customer findByPhoneAndFirstName(String firstName, String phone);
}
