package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductServiceFeignClient {
    @GetMapping("/products/{productId}")
    String getProductInfo(@PathVariable("productId") Long id);
}

