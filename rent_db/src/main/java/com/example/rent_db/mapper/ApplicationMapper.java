package com.example.rent_db.mapper;

import com.example.rent_db.model.dto.CreateApartmentsDto;
import com.example.rent_db.model.dto.FullApartmentsInfo;
import com.example.rent_db.model.entity.AddressEntity;
import com.example.rent_db.model.entity.ApartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper {

    FullApartmentsInfo mappingAddressEntityAndApartmentEntity(AddressEntity addressEntity, ApartmentEntity apartmentEntity);

    AddressEntity mappingCreateApartmentsDto(CreateApartmentsDto createApartmentsDto);

    ApartmentEntity mappingCreateApartmentDto(CreateApartmentsDto createApartmentsDto);

}
