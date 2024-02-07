package com.example.product.constant;

import ch.qos.logback.core.sift.AppenderFactoryUsingSiftModel;

public class ProductConstant {
    public static final String TEXT_MESSAGE = "Спасибо за бронирование на нашем сайте. " +
            "%tD Вами были забронированы аппартаменты. " +
            "Аппартаменты находятся по адресу %s ";
    public static final String DISCOUNT_PRODUCTS = "Аппартаменты забронированы на %d дней, общая сумма %d рублей," +
            "скидка за %s- %d рублей. С учетом скидки итоговая сумма составляет %d .";

    public static final String MESSAGE_SUBJECT = "Бронирование аппартаментов";
}
