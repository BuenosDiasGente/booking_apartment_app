package com.example.rent_db.exception;

import lombok.Getter;


public class ExitsApartmentException extends RuntimeException{


   public ExitsApartmentException(String message) {
      super(message);
   }
}
