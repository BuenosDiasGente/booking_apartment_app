package com.example.rent_db.repository;

import com.example.rent_db.model.entity.IntegrationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationInfoRepository extends JpaRepository<IntegrationInfo,String> {

}
