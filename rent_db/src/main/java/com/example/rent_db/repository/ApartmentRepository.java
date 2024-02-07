package com.example.rent_db.repository;

import com.example.rent_db.model.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {

    /**
     * Native query
     */
//    @Query(nativeQuery = true, value = "SELECT*FROM apartment_info WHERE price <=3000 ORDER BY price = :price")
//    List<ApartmentEntity> findAllApartmentsPriceUpTo3000(Integer price);
//

    /**
     * JPQL query
     */

    @Query(value = "SELECT a FROM ApartmentEntity a WHERE a.price = :price")
    List<ApartmentEntity> findAllApartmentsPriceFilter(Integer price);

    @Query(value = "SELECT a FROM ApartmentEntity a WHERE a.countRooms = :countRooms AND a.price = :price")
    List<ApartmentEntity> findAllApartmentsCountRoomsAndPriceFilter(Integer countRooms, Integer price);

}
