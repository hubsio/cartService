package com.example.demo.dto;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductInfo {
    private Long id;
    private String name;
    private BigDecimal price;
}
