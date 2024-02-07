package com.example.rent_db.model.entity;

import com.example.rent_db.model.entity.AddressEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "apartment_info")
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ApartmentEntity {

    @Id
    @SequenceGenerator(name = "apartment_infoSequence", sequenceName = "apartment_info_sequence", allocationSize = 1,
            initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_infoSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "count_rooms")
    private Integer countRooms;

    @Column(name = "price")
    private Integer price;

    @Column(name = "global_rating")
    private String globalRating;

    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

    @Column(name = "availability")
    private boolean availability;

    @OneToOne(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressEntity addressEntity;

}
