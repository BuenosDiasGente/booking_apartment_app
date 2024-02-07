package com.example.rent_db.controller;

import com.example.rent_db.model.AbstractResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UtilMethod {
    public static <R extends AbstractResponse> ResponseEntity<R> wrapResponse(R response) {
        return new ResponseEntity<>(response, getStatus(response));
    }

    public static <R extends AbstractResponse> HttpStatus getStatus(R response) {
        return response.getError()!=null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
    }
}
