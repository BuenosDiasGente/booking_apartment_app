package com.example.rent_db.service.impl;

import com.example.rent_db.exception.NotFoundConfigException;
import com.example.rent_db.model.entity.IntegrationInfo;
import com.example.rent_db.model.geoCoderModels.GeocoderResponse;
import com.example.rent_db.repository.IntegrationInfoRepository;
import com.example.rent_db.service.RestTemplateManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.rent_db.base64.EncoderAndDecoder.applicationDecoder;

@Service
@RequiredArgsConstructor
public class RestTemplateManagerImpl implements RestTemplateManager {
    private Logger log = LoggerFactory.getLogger(RestTemplateManagerImpl.class);

    private final IntegrationInfoRepository integrationInfoRepository;

    private final String ID_GEOCODER = "geocode_service";
    private final String ID_PRODUCT = "product_service";


    @Override
    public GeocoderResponse getInfoByLocation(String latitude, String longitude) {
        RestTemplate restTemplate = new RestTemplate();
        IntegrationInfo param = getIntegrationParam(ID_GEOCODER);
        GeocoderResponse value = restTemplate.exchange(prepareUrlForLocation(latitude, longitude, param),
                HttpMethod.GET,
                new HttpEntity<>(null),
                GeocoderResponse.class).getBody();
        return value;
    }

    private IntegrationInfo getIntegrationParam(String id) {
        log.info("RestTemplateManagerImpl:-> getIntegrationParam");

        IntegrationInfo integrationData = integrationInfoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("RestTemplateManagerImpl: getIntegrationParam: 'integrationData' NotFound");
                    return new NotFoundConfigException();
                });
        String apiKey = applicationDecoder(integrationData.getApiKey());
        integrationData.setApiKey(apiKey);
        log.info("RestTemplateManagerImpl:<- getIntegrationParam");
        return integrationData;
    }


    private String prepareUrlForLocation(String latitude, String longitude, IntegrationInfo integrationData) {
        return String.format(integrationData.getUrl(), latitude, longitude, integrationData.getApiKey());
    }

    @Override
    public String prepareProductForBooking(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        IntegrationInfo param = getIntegrationParam(ID_PRODUCT);
        String value = restTemplate.exchange(prepareUrlForProductServer(id, param),
                HttpMethod.GET,
                new HttpEntity<>(null),
                String.class).getBody();
        return value;
    }

    private String prepareUrlForProductServer(Long id, IntegrationInfo integrationInfo) {

        return String.format(integrationInfo.getUrl(), id, integrationInfo.getApiKey());
    }


}
