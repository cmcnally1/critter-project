package com.udacity.jdnd.course3.critter.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to throw when a Schedule is not found
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Appointment not found")
public class ScheduleNotFoundException extends RuntimeException{

    public ScheduleNotFoundException(){}

    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
