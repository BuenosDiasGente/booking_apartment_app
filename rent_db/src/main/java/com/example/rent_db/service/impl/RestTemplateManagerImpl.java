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


    @Override
    public GeocoderResponse getInfoByLocation(String latitude, String longitude) {
        RestTemplate restTemplate = new RestTemplate();
        GeocoderResponse value = restTemplate.exchange(prepareUrl(latitude, longitude),
                HttpMethod.GET,
                new HttpEntity<>(null),
                GeocoderResponse.class).getBody();
        return value;
    }

    private String prepareUrl(String latitude, String longitude) {

        log.info("RestTemplateManagerImpl:-> prepareUrl");

        IntegrationInfo integrationData = integrationInfoRepository.findById(ID_GEOCODER)
                .orElseThrow(() -> {
                    log.error("RestTemplateManagerImpl: prepareUrl: 'integrationData' NotFound");
                    return new NotFoundConfigException();
                });
        String apiKey = applicationDecoder(integrationData.getApiKey());

        log.info("RestTemplateManagerImpl:<- prepareUrl");
        return String.format(integrationData.getUrl(), latitude, longitude, apiKey);
    }

}
