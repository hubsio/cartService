package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductServiceFeignClient {
    @GetMapping("/phones/{productId}")
    Long getProductInfo(@PathVariable("productId") Long productId);
}
