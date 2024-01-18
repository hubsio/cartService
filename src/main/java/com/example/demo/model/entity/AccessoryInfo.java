package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class AccessoryInfo {
    private Long id;
    private String name;
    private BigDecimal price;
}
