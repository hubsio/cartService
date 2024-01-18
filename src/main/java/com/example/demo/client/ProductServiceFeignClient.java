package com.example.demo.client;

import com.example.demo.model.entity.AccessoryInfo;
import com.example.demo.model.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Optional;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductServiceFeignClient {
    @GetMapping("/products/{productId}")
    Optional<Long> getProductInfo(@PathVariable("productId") Long id);

    @GetMapping("/accessories/{accessoryId}")
    Optional<Long> getAccessoryInfo(@PathVariable("accessoryId") Long id);
}

