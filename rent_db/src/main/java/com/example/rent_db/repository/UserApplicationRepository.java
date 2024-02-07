package com.example.rent_db.repository;

import com.example.rent_db.model.entity.UserApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserApplicationRepository extends JpaRepository<UserApplicationEntity, Long> {

    /**Spring Data generation*/
    //  UserApplicationEntity findAllByNikName(String nik);
    // UserApplicationEntity findAllByLogin(String login);

    /**
     * Native query
     */
    @Query(nativeQuery = true, value = "SELECT*FROM user_application WHERE login = :login")
    UserApplicationEntity findUserByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT *FROM user_application WHERE nik_name = :nik")
    UserApplicationEntity findAllByNikName(String nik);

    /**
     * JPQL query
     */
    @Query(value = "SELECT u FROM UserApplicationEntity u WHERE u.nikName = :nik")
    UserApplicationEntity findUserByNikName(String nik);

    @Query(value = "SELECT u FROM UserApplicationEntity u WHERE u.login = :login")
    UserApplicationEntity findAllByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT*FROM user_application WHERE login = :login AND password = :password")
    UserApplicationEntity findUserByRegistration(String login, String password);

    @Query(value = "SELECT u FROM UserApplicationEntity u WHERE u.token is not null")
    List<UserApplicationEntity> findUserApplicationEntitiesByTokenIsEmpty();

    @Query(value = "SELECT u FROM UserApplicationEntity u WHERE u.token = :token")
   Optional<UserApplicationEntity>  findUserApplicationEntitiesByToken(String token);

}
