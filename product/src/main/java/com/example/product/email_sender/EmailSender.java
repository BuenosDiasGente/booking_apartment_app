package com.example.product.email_sender;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;

    /**
     * метод рассылки сообщений
     * @param subject
     * @param text
     * @param sendTo
     */
    public void sendEmail(String subject,String text,String sendTo){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("morkovka29@yandex.ru");
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
    }

//    public String formationMessage(){
//        return null;
//
//    }
}
