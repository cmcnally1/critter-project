package com.udacity.jdnd.course3.critter.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to throw when Customer not found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Customer not found that matches ID")
public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException() {

    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
