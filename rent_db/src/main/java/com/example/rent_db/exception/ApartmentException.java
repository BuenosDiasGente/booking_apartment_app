package com.example.rent_db.exception;

import lombok.Getter;

@Getter
public class ApartmentException extends RuntimeException {

    public ApartmentException(String message) {
        super(message);
    }
}
