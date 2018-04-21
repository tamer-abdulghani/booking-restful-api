/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.models.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Tamer
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Booking Contains No products! ")
public class BookingContainsNoProducts extends APIException {

    public BookingContainsNoProducts(int code, String message) {
        super(code, message);
    }

}
