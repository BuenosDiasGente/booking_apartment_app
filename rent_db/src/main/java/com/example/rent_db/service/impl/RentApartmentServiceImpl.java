package com.example.rent_db.service.impl;

import com.example.rent_db.exception.ApartmentException;
import com.example.rent_db.exception.ExitsApartmentException;
import com.example.rent_db.mapper.ApplicationMapper;
import com.example.rent_db.model.dto.*;
import com.example.rent_db.model.entity.AddressEntity;
import com.example.rent_db.model.entity.ApartmentEntity;
import com.example.rent_db.model.entity.BookingHistory;
import com.example.rent_db.model.entity.UserApplicationEntity;
import com.example.rent_db.model.geoCoderModels.Components;
import com.example.rent_db.model.geoCoderModels.GeocoderResponse;
import com.example.rent_db.repository.AddressRepository;
import com.example.rent_db.repository.ApartmentRepository;
import com.example.rent_db.repository.BookingRepository;
import com.example.rent_db.repository.UserApplicationRepository;
import com.example.rent_db.service.AuthService;
import com.example.rent_db.service.RentApartmentService;
import com.example.rent_db.service.RestTemplateManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.rent_db.constant.RentDbConstant.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RentApartmentServiceImpl implements RentApartmentService {
    private Logger log = LoggerFactory.getLogger(RentApartmentServiceImpl.class);

    private final AddressRepository addressRepository;
    private final ApplicationMapper applicationMapper;
    private final RestTemplateManager restTemplateManager;
    private final EntityManager entityManager;
    private final ApartmentRepository apartmentRepository;
    private final AuthService authService;
    private final BookingRepository bookingRepository;


    /**
     * метод фильтрует поиск апартаментов по городу
     *
     * @param city
     * @return
     */
    @Override
    public SearchApartmentsResponseDto searchApartments(String city) {
        List<AddressEntity> listAddressEntity = addressRepository.findApartmentByCity(city);
        List<FullApartmentsInfo> resultList = new ArrayList<>();
        for (AddressEntity address : listAddressEntity) {
            FullApartmentsInfo fullApartmentsInfo = applicationMapper.mappingAddressEntityAndApartmentEntity(address, address.getApartment());
            resultList.add(fullApartmentsInfo);
        }
        return new SearchApartmentsResponseDto(resultList);
    }

    /**
     * метод фильтрует поиск апартаментов по городу и количеству комнат
     *
     * @param city
     * @param countRooms
     * @return
     */
    @Override
    public SearchApartmentsResponseDto searchApartments(String city, Integer countRooms) {
        List<AddressEntity> addresList = addressRepository.findAllApartmentsBYCountRoomsAndCityFilter(city, countRooms);
        List<FullApartmentsInfo> resultList = new ArrayList<>();
        for (AddressEntity address : addresList) {
            FullApartmentsInfo fullApartmentsInfo = applicationMapper.mappingAddressEntityAndApartmentEntity(address, address.getApartment());
            resultList.add(fullApartmentsInfo);
        }
        return new SearchApartmentsResponseDto(resultList);
    }

    /**
     * метод фильтрует поиск апартаментов по городу и цене
     *
     * @param city
     * @param price
     * @return
     */
    @Override
    public SearchApartmentsResponseDto searchApartmentsPrice(String city, Integer price) {
        List<AddressEntity> addressList = findAddressListWithCriteria(city, price);
        List<FullApartmentsInfo> resultList = new ArrayList<>();
        for (AddressEntity address : addressList) {
            FullApartmentsInfo fullApartmentsInfo = applicationMapper.mappingAddressEntityAndApartmentEntity(address, address.getApartment());
            resultList.add(fullApartmentsInfo);
        }
        return new SearchApartmentsResponseDto(resultList);
    }

    /**
     * Поиск апартаментов по id
     *
     * @param id
     * @return
     */
    @Override
    public ApartmentEntity searchApartmentById(Long id) {
        log.info("RentApartmentServiceImpl: ->searchApartmentById");

        ApartmentEntity apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("RentApartmentServiceImpl: searchApartmentId: Not found apartment by " + id);
                    return new ApartmentException(ID_APARTMENT_ERROR);
                });
        log.info("RentApartmentServiceImpl: <-searchApartmentById");
        return apartment;
    }

    @Override
    public FullApartmentsInfo prepareFullApartmentInfo(ApartmentEntity apartment) {
        return applicationMapper.mappingAddressEntityAndApartmentEntity(apartment.getAddressEntity(), apartment);

    }

    /**
     * метод фильтрует поиск апартаментов по городу, количеству комнат и цене
     *
     * @param city
     * @param countRooms
     * @param price
     * @return
     */
    @Override
    public SearchApartmentsResponseDto searchApartments(String city, Integer countRooms, Integer price) {
        List<AddressEntity> addresList = addressRepository.findAllApartmentsBYPriceAndCountRoomsAndCityFilter(city, countRooms, price);
        List<FullApartmentsInfo> resultList = new ArrayList<>();
        for (AddressEntity address : addresList) {
            FullApartmentsInfo fullApartmentsInfo = applicationMapper.mappingAddressEntityAndApartmentEntity(address, address.getApartment());
            resultList.add(fullApartmentsInfo);
        }
        return new SearchApartmentsResponseDto(resultList);
    }

    /**
     * метод поиска апартаментов по геолакации
     *
     * @param latitude
     * @param longitude
     * @return
     */
    @Override
    public SearchApartmentsResponseDto searchApartmentsByLoc(String latitude, String longitude) {
        GeocoderResponse infoByLocation = restTemplateManager.getInfoByLocation(latitude, longitude);
        String cityInfo = searchCityInfo(infoByLocation);
        List<AddressEntity> addresList = addressRepository.findApartmentByCity(cityInfo);
        List<FullApartmentsInfo> resultList = new ArrayList<>();
        for (AddressEntity address : addresList) {
            FullApartmentsInfo fullApartmentsInfo = applicationMapper.mappingAddressEntityAndApartmentEntity(address, address.getApartment());
            resultList.add(fullApartmentsInfo);
        }
        return new SearchApartmentsResponseDto(resultList);
    }

    /**
     * Добавление новых апартаментов.
     *
     * @param id
     * @param createApartmentsDto
     * @return
     */
    @Override
    public FullApartmentsInfo addApartment(Long id, CreateApartmentsDto createApartmentsDto) {
        log.info("RentApartmentServiceImpl: ->addApartment");

        boolean present = apartmentRepository.findById(id).isPresent();

        ApartmentEntity apartment = applicationMapper.mappingCreateApartmentDto(createApartmentsDto);
        AddressEntity address = applicationMapper.mappingCreateApartmentsDto(createApartmentsDto);
        if (present) {
            log.info("RentApartmentServiceImpl: <-addApartment");
            return null;

        } else {
            apartment.setDateRegistration(LocalDateTime.now());
            apartment.setAvailability(true);
            address.setApartment(apartment);
            apartmentRepository.save(apartment);
            addressRepository.save(address);
            FullApartmentsInfo fullApartmentsInfo = applicationMapper.mappingAddressEntityAndApartmentEntity(address, apartment);
            log.info("RentApartmentServiceImpl: <-addApartment");
            return fullApartmentsInfo;
        }
    }

    /**
     * метод бронирования аппартаментов
     *
     * @param id
     * @param bookingDto
     * @param token
     * @return
     */
    @Override
    public BookingResponse bookingApartment(Long id, BookingDto bookingDto, String token) {
        UserApplicationEntity userApplication = authService.checkToken(token);
        ApartmentEntity apartment = searchApartmentById(id);
        checkAvailability(apartment);

        BookingHistory bookingHistory = new BookingHistory(bookingDto.getStartBooking(),
                bookingDto.getEndBooking(),
                apartment.getPrice(),
                userApplication,
                apartment);
        bookingRepository.save(bookingHistory);

        FullApartmentsInfo apartmentsInfo = applicationMapper.mappingAddressEntityAndApartmentEntity(apartment.getAddressEntity(), apartment);
        try {
            restTemplateManager.prepareProductForBooking(bookingHistory.getId());
            return new BookingResponse(BOOKING_WITH_PRODUCT_DONE, apartmentsInfo);
        } catch (Exception e) {
            //продюсер
            return new BookingResponse(BOOKING_WITH_OUT_PRODUCT_DONE, apartmentsInfo);
        }
    }

    /**
     * метод проверяет свободны или забронированы апартаменты
     *
     * @param apartment
     */
    private void checkAvailability(ApartmentEntity apartment) {

        if (!apartment.isAvailability()) {
            throw new ApartmentException(NON_FREE_APARTMENT);
        }
        apartment.setAvailability(false);
        apartmentRepository.save(apartment);
    }


    /**
     * criteria API
     *
     * @param city
     * @param price
     * @return
     */
    private List<AddressEntity> findAddressListWithCriteria(String city, Integer price) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AddressEntity> query = criteriaBuilder.createQuery(AddressEntity.class);
        Root<AddressEntity> root = query.from(AddressEntity.class);

        query.select(root)
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(root.get("city"), city),
                                criteriaBuilder.equal(root.get("apartment").get("price"), price))
                );
        return entityManager.createQuery(query).getResultList();
    }


    private String searchCityInfo(GeocoderResponse value) {
        Components components = value.getResultsList().get(0).getComponents();
        if (!isNull(components.getCity())) {
            return components.getCity();
        } else {
            return components.getTown();
        }
    }

}


