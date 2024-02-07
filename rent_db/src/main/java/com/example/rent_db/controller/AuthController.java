package com.example.rent_db.controller;

import com.example.rent_db.model.dto.AuthDto;
import com.example.rent_db.model.entity.UserApplicationEntity;
import com.example.rent_db.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.rent_db.constant.RentDbConstant.AUTH_URL;
import static com.example.rent_db.constant.RentDbConstant.REGISTRATION_USER;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Регистрация нового пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новый пользователь зарегистрирован",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserApplicationEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "BadRequest"
                    )
            }
    )
    @PostMapping(REGISTRATION_USER)
    public String registerUser(@RequestBody UserApplicationEntity user) {
        return authService.registerUser(user);
    }

    @PostMapping(AUTH_URL)
    public String authUser(@RequestBody AuthDto user) {
        return authService.authUser(user);
    }
}
