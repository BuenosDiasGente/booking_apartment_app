package com.example.rent_db.repository;

import com.example.rent_db.model.entity.AddressEntity;
import com.example.rent_db.model.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query(value = "SELECT a FROM AddressEntity a WHERE a.city = :city")
    List<AddressEntity> findApartmentByCity(String city);

    @Query(value = "SELECT a FROM AddressEntity a WHERE a.city = :city AND a.apartment.countRooms = :countRooms")
    List<AddressEntity> findAllApartmentsBYCountRoomsAndCityFilter(String city, Integer countRooms);

    @Query(value = "SELECT a FROM AddressEntity a WHERE a.city = :city AND a.apartment.price = :price")
    List<AddressEntity> findAllApartmentsBYPriceAndCityFilter(String city, Integer price);

    @Query(value = "SELECT a FROM AddressEntity a WHERE a.city = :city AND a.apartment.countRooms = :countRooms AND a.apartment.price = :price")
    List<AddressEntity> findAllApartmentsBYPriceAndCountRoomsAndCityFilter(String city, Integer countRooms,Integer price);
}
