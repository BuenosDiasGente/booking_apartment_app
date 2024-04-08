package com.example.product.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
public class ProductTopicConsumerListener {
    private final CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "Product_topic", groupId = "kafka-sandbox")
    public void listen(String message) {
        messages.add(message);
    }
    public CopyOnWriteArrayList<String> getMessages() {

        return messages;
    }
}
