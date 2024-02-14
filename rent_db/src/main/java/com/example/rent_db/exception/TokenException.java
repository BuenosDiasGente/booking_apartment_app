package com.example.rent_db.exception;

import lombok.Getter;

@Getter
public class TokenException extends RuntimeException{
    private final String NOT_FOUND_TOKEN="Для бронирования апартаментов необходимо авторизироваться!";

}
