package com.example.rent_db.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "booking_history")
public class BookingHistory {

    @Id
    @SequenceGenerator(name = "booking_historySequence", sequenceName = "booking_history_sequence", allocationSize = 1,
            initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_historySequence")
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
