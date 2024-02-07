package com.example.rent_db.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApartmentsDto {
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;
    private Integer countRooms;
    private Integer price;
    private boolean availability;
}
