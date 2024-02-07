package com.example.rent_db.service;

import com.example.rent_db.model.dto.AuthDto;
import com.example.rent_db.model.entity.UserApplicationEntity;

public interface AuthService {
    String registerUser(UserApplicationEntity user);

    String authUser(AuthDto user);
}
