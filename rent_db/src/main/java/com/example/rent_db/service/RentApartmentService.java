package com.example.rent_db.service;

import com.example.rent_db.exception.ApartmentException;
import com.example.rent_db.model.dto.CreateApartmentsDto;
import com.example.rent_db.model.dto.FullApartmentsInfo;
import com.example.rent_db.model.dto.SearchApartmentsResponseDto;
import com.example.rent_db.model.entity.AddressEntity;
import com.example.rent_db.model.entity.ApartmentEntity;
import com.example.rent_db.model.entity.UserApplicationEntity;

public interface RentApartmentService {

    SearchApartmentsResponseDto searchApartments(String city);

    SearchApartmentsResponseDto searchApartments(String city, Integer countRooms);

    SearchApartmentsResponseDto searchApartmentsPrice(String city, Integer price);

    FullApartmentsInfo searchApartmentById(Long id);

    String addApartment(Long id,CreateApartmentsDto createApartmentsDto);

    SearchApartmentsResponseDto searchApartments(String city, Integer countRooms, Integer price);

    SearchApartmentsResponseDto searchApartmentsByLoc(String latitude, String longitude);

}
