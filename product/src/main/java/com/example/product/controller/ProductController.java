package com.example.product.controller;

import com.example.product.email_sender.EmailSender;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final EmailSender emailSender;
    private final ProductService productService;

    @GetMapping("/test")
    public String test() {
        emailSender.sendEmail("тема письма", "текст", "serggeipetrov@gmail.com");
        return "Test";
    }

    //http://localhost:9093/prepare-product-for-booking?id=%s&tokenValue=%s
    @GetMapping("/prepare-product-for-booking")
    public void prepareProduct(@RequestParam String id,
                               @RequestParam String token) {
        productService.prepareProduct(id);
        //сервис по проверке токена
    }


//    Optional<OrderProduct> orderProductOptional = orderDto.getProducts().stream().findFirst();
//        orderProductOptional.ifPresent(orderProduct -> {
//        putRequiredAttributeIfNotNull(orderProductV3.getAttributes(), "mrp", orderProduct.getMrp());
//        // добавьте другие атрибуты, если необходимо
//    });

}
