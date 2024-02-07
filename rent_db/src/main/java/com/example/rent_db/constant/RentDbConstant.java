package com.example.rent_db.constant;

public class RentDbConstant {

    /**
     * Constant path
     */
    public static final String BASE_URL = "/registration";
    public static final String REGISTRATION_USER = BASE_URL + "/registration-user";
    public static final String AUTH_URL = BASE_URL + "/auth-user";
    public static final String SEARCH_APARTMENT = BASE_URL + "/apartment-filters";
    public static final String BOOKING_APARTMENT = BASE_URL + "/booking-apartment";
    public static final String SEARCH_APARTMENT_BY_LOCATION = BASE_URL + "/apartment-location";
    public static final String ADD_NEW_APARTMENT = BASE_URL + "/{id}/apartment-ad";

    /**
     * Constant message exception
     */
    public static final String NICKNAME_REGISTRATION_ERROR = "Такой nik-name занят,введите другой.";
    public static final String LOGIN_REGISTRATION_ERROR = "Такой login занят,введите другой";
    public static final String INVALID_PARAMS_ERROR = "Неверные параметры входа";
    public static final String ID_APARTMENT_ERROR = "Апартаменты не найдены";
    public static final String APARTMENT_EXIST = "Данные апратаменты, числяться в базе данных";


    /**
     * Constant message
     */
    public static final String REGISTRATION_DONE = "Пользователь зарегистрирован.";

    public static final String APARTMENT_CREATE = "Апрартаменты добавлены";

}
