package com.example.rent_db.base64;

import java.util.Base64;

public class EncoderAndDecoder {

    public static String applicationDecoder(String value){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(value);
        return new String(decode);
    }
}
