package com.example.rent_db.kafka.kafka_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServiceController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // толькои методы общения get? или др.  через path отправляется или через другие?
    @GetMapping()
    public String getMessageToTopic(@PathVariable String message) {
        try {
            kafkaTemplate.send("Product_topic", message);
        } catch (Exception e) {
            return "Сообщение не опубликовано";
        }
        return "Сообщение опубликовано";
    }
}
