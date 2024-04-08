package com.example.product.controller;

import com.example.product.email_sender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final EmailSender emailSender;

    @GetMapping("/prepare-product-for-booking")
    public String prepareProduct(Long id, String tokenValue) {
      //  emailSender.sendEmail("тема письма","текст","serggeipetrov@gmail.com");
        return "Test";
    }

}
