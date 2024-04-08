package com.example.product.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ProductTopicConsumerListener productTopicConsumerListener;

    @GetMapping()
    public CopyOnWriteArrayList<String> getMassage() {
        CopyOnWriteArrayList<String> messages = productTopicConsumerListener.getMessages();
        return messages;
    }
}
