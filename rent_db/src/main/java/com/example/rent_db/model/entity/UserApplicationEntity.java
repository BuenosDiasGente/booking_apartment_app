package com.example.rent_db.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "user_application")
public class UserApplicationEntity {
    @Id
    @SequenceGenerator(name = "user_applicationSequence", sequenceName = "user_application_sequence", allocationSize = 1,
            initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_applicationSequence")
    private Long id;

    @Column(name = "nik_name",unique = true)
    private String nikName;

    @Column(name = "login",unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "booking_count")
    private Integer bookingCount;

    @Column(name = "parent_city")
    private String parentCity;

    @Column(name="token")
    private String token;

    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

    public UserApplicationEntity(Long id, String nikName, String login, String password, Integer bookingCount, String parentCity) {
        this.id = id;
        this.nikName = nikName;
        this.login = login;
        this.password = password;
        this.bookingCount = bookingCount;
        this.parentCity = parentCity;
    }

    public UserApplicationEntity() {

    }
}
