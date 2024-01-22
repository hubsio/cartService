package com.example.demo.client;

import com.example.demo.dto.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductServiceFeignClient {
    @GetMapping("/products/{productId}")
    Optional<ProductInfo> getProductInfo(@PathVariable("productId") Long id);
}

