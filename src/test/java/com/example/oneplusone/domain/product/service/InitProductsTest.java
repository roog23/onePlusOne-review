package com.example.oneplusone.domain.product.service;

import com.example.oneplusone.domain.auth.entity.User;
import com.example.oneplusone.domain.auth.enums.UserRole;
import com.example.oneplusone.domain.auth.repository.UserRepository;
import com.example.oneplusone.domain.product.entity.Product;
import com.example.oneplusone.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InitProductsTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @Test
    @DisplayName("5만개의 더미 데이터 생성")
    void createProducts() {
        User user = new User("admin", "admin", "password", UserRole.SELLER,"","","");
        User saveUser = userRepository.save(user);
        List<Product> productList = new ArrayList<>();
        for(int i = 1; i <= 50000; i ++){
            String name;

            if (i % 10 == 0) {
                name = "special product" + i;
            } else if (i % 10 == 1) {
                name = "good product" + i;
            } else {
                name = "product" + i;
            }

            String type;
            if (i % 10 == 0) {
                type = "과일";
            } else if (i % 10 == 1) {
                type = "음료";
            } else if (i % 10 == 2) {
                type = "전자기기";
            }else if (i % 10 == 3) {
                type = "생필품";
            }else if (i % 10 == 4) {
                type = "냉동식품";
            }else if (i % 10 == 5) {
                type = "냉장식품";
            }else if (i % 10 == 6) {
                type = "건조식품";
            }else if (i % 10 == 7) {
                type = "필기도구";
            }else if (i % 10 == 8) {
                type = "청소용품";
            }else {
                type = "과자";
            }

            Long price = 1000L + (i % 9001);
            Long quantity = 1L + i;
            Product product = new Product(name, type, price, quantity, saveUser);
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }
}
