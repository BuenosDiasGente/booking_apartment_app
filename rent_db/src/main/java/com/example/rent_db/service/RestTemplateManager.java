package com.example.rent_db.service;

import com.example.rent_db.model.geoCoderModels.GeocoderResponse;

public interface RestTemplateManager {

    GeocoderResponse getInfoByLocation(String latitude, String longitude);
}
