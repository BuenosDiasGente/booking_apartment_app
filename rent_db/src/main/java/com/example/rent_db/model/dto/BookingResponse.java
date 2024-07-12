package com.example.rent_db.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingResponse {
    private String message;
    private FullApartmentsInfo fullApartmentsInfo;
}
