package com.example.rent_db.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "booking_history")
public class BookingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "check_in")
    private LocalDateTime checkIn;
    @Column(name = "check_out")
    private LocalDateTime checkOut;
    @Column(name = "price_day")
    private Integer priceDay;
    @Column(name = "total_discount")
    private Integer totalValueDiscount;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product products;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserApplicationEntity user;

    @ManyToOne()
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;

    public BookingHistory(LocalDateTime checkIn, LocalDateTime checkOut, Integer priceDay, UserApplicationEntity user, ApartmentEntity apartment) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.priceDay = priceDay;
        this.user = user;
        this.apartment = apartment;
    }
}
