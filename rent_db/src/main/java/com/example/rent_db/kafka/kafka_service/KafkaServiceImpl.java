package com.example.rent_db.kafka.kafka_service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public String getMessageToTopic(String message) {
        try {
            kafkaTemplate.send("Product_topic", message);
        } catch (Exception e) {
            return "Сообщение не опубликовано";
        }
        return "Сообщение опубликовано";
    }
}
