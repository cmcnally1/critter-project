package com.cmcnally.critter.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to throw when Pet not found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Pet not found that matches ID")
public class PetNotFoundException extends RuntimeException{

    public PetNotFoundException() {

    }

    public PetNotFoundException(String message) {
        super(message);
    }
}
