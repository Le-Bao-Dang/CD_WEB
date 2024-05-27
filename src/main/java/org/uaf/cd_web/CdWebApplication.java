package org.uaf.cd_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.uaf.cd_web.services.ProductServiceImp;

@SpringBootApplication
public class CdWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(CdWebApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(CdWebApplication.class, args);

//        // Lấy ProductService từ context
//        ProductServiceImp productService = context.getBean(ProductServiceImp.class);
//
//        System.out.println(productService.getProductById("prod10"));
    }

}
