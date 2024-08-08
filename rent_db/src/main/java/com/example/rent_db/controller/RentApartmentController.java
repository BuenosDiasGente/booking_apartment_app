package com.example.rent_db.controller;

import com.example.rent_db.model.dto.*;
import com.example.rent_db.model.entity.ApartmentEntity;
import com.example.rent_db.service.AuthService;
import com.example.rent_db.service.RentApartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.example.rent_db.constant.RentDbConstant.*;
import static com.example.rent_db.controller.UtilMethod.wrapResponse;
import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
public class RentApartmentController {
    private Logger log = LoggerFactory.getLogger(RentApartmentController.class);

    private final RentApartmentService userApplicationEntityService;


//
//    @GetMapping(SEARCH_APARTMENT)
//    public ResponseEntity<SearchApartmentsResponseDto> searchApartments(@RequestParam(required = false) Integer price,
//                                                                        @RequestParam String city,
//                                                                        @RequestParam(required = false) Integer countRooms) {
//        if (isNull(price) && isNull(countRooms)) {
//            return wrapResponse(userApplicationEntityService.searchApartments(city));
//        }
//        if (isNull(price) && !isNull(countRooms)) {
//            return wrapResponse(userApplicationEntityService.searchApartments(city, countRooms));
//        }
//        if (isNull(countRooms) && !isNull(price)) {
//            return wrapResponse(userApplicationEntityService.searchApartmentsPrice(city, price));
//        }
//        return wrapResponse(userApplicationEntityService.searchApartments(city, countRooms, price));
//    }
//
//    @PostMapping(BOOKING_APARTMENT)
//    public BookingResponse bookingApartment(@RequestParam Long id,
//                                            @RequestBody(required = false) BookingDto bookingDto,
//                                            @RequestHeader(required = false) String token) {
//        if (isNull(bookingDto)) {
//            ApartmentEntity apartment = userApplicationEntityService.searchApartmentById(id);
//
//            return new BookingResponse(null, userApplicationEntityService.prepareFullApartmentInfo(apartment));
//        } else {
//
//            return userApplicationEntityService.bookingApartment(id, bookingDto, token);
//
//        }
//    }
//
//
//    @GetMapping("/t")
//    public LocalDateTime getLocalDateTime(){
//        return LocalDateTime.now();
//    }
//
//    @GetMapping(SEARCH_APARTMENT_BY_LOCATION)
//    public ResponseEntity<SearchApartmentsResponseDto> searchApartmentsByLocation(@RequestParam String latitude,
//                                                                                  @RequestParam String longitude) {
//        return wrapResponse(userApplicationEntityService.searchApartmentsByLoc(latitude, longitude));
//    }
//
//    @PostMapping(ADD_NEW_APARTMENT)
//    public ResponseEntity<FullApartmentsInfo> addApartment(@PathVariable Long id,
//                                                           @RequestBody CreateApartmentsDto createApartmentsDto) {
//        return ResponseEntity.ok(userApplicationEntityService.addApartment(id, createApartmentsDto));
//    }

    @GetMapping("/test")
    public void checkLog() {
        log.info("Info log");
        log.error("Error log");
    }


}
