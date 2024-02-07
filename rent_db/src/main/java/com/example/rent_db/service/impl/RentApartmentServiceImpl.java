package com.example.rent_db.service.impl;

import com.example.rent_db.exception.ApartmentException;
import com.example.rent_db.mapper.ApplicationMapper;
import com.example.rent_db.model.dto.CreateApartmentsDto;
import com.example.rent_db.model.dto.FullApartmentsInfo;
import com.example.rent_db.model.dto.SearchApartmentsResponseDto;
import com.example.rent_db.model.entity.AddressEntity;
import com.example.rent_db.model.entity.ApartmentEntity;
import com.example.rent_db.model.geoCoderModels.Components;
import com.example.rent_db.model.geoCoderModels.GeocoderResponse;
import com.example.rent_db.repository.AddressRepository;
import com.example.rent_db.repository.ApartmentRepository;
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
     * @param id
     * @return
     */
    @Override
    public FullApartmentsInfo searchApartmentById(Long id) {
        log.info("RentApartmentServiceImpl: ->searchApartmentById");

        ApartmentEntity apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("RentApartmentServiceImpl: searchApartmentId: Not found apartment by " + id);
                    return new ApartmentException(ID_APARTMENT_ERROR);
                });
        FullApartmentsInfo apartmentInfo = applicationMapper.mappingAddressEntityAndApartmentEntity(apartment.getAddressEntity(), apartment);
        log.info("RentApartmentServiceImpl: <-searchApartmentById");
        return apartmentInfo;
    }


    /**
     * Добавление новых апартаментов.
     * @param id
     * @param createApartmentsDto
     * @return
     */
    @Override
    public String addApartment(Long id, CreateApartmentsDto createApartmentsDto) {
        log.info("RentApartmentServiceImpl: ->addApartment");

        boolean present = apartmentRepository.findById(id).isPresent();

        ApartmentEntity apartment = applicationMapper.mappingCreateApartmentDto(createApartmentsDto);
        AddressEntity address = applicationMapper.mappingCreateApartmentsDto(createApartmentsDto);
        if (present) {
            log.info("RentApartmentServiceImpl: <-addApartment");
            return APARTMENT_EXIST;

        } else {
            apartment.setDateRegistration(LocalDateTime.now());
            apartment.setAvailability(true);
            address.setApartment(apartment);
            apartmentRepository.save(apartment);
            addressRepository.save(address);
            log.info("RentApartmentServiceImpl: <-addApartment");
            return APARTMENT_CREATE;
        }
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

    private String searchCityInfo(GeocoderResponse value) {
        Components components = value.getResultsList().get(0).getComponents();
        if (!isNull(components.getCity())) {
            return components.getCity();
        } else {
            return components.getTown();
        }
    }

}


