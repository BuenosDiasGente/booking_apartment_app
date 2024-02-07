package com.example.rent_db.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDto {
    private LocalDateTime startBooking;
    private LocalDateTime endBooking;
}
