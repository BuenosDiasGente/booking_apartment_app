package com.example.rent_db.model.entity;

import com.example.rent_db.model.AbstractResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="address_info")
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class AddressEntity  {
    @Id
    @SequenceGenerator(name="address_infoSequence",sequenceName = "address_info_sequence",allocationSize = 1,initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_infoSequence")
    @Column(name="id")
    private Long id;

    @Column(name="region")
    private String region;

    @Column(name="city")
    private String city;

    @Column(name="street")
    private String street;

    @Column(name="house_number")
    private String houseNumber;

    @Column(name="flat_number")
    private String flatNumber;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="apartment_id")
    private ApartmentEntity apartment;

}
