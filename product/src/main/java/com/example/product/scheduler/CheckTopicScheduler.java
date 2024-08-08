package com.example.product.scheduler;

import com.example.product.kafka.ProductTopicConsumerListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class CheckTopicScheduler {


    private final ProductTopicConsumerListener productTopicConsumerListener;
    //запуск с задержкой
    @Scheduled(fixedDelay = 60000L)
    public void checkTopic() {
        log.info("Планировщик CheckTopicScheduler начал свою работу");
        CopyOnWriteArrayList<String> messages = productTopicConsumerListener.getMessages();
        if(messages.isEmpty()){
            log.info("данных для постобработки нет");
        }
        //если лист не пустой передаем данные на обработку в сервис

    }




}
