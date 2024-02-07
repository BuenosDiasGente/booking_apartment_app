package com.example.product.email_sender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfig {

    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.yandex.ru");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("morkovka29@yandex.ru");
        javaMailSender.setPassword("cshvoaitykwprzjj");

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.setProperty("mail.transport.protocol","smtps");
        properties.setProperty("mail.debug","true");
        return javaMailSender;
    }
}
