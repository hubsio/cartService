package com.example.demo.model.entity;

import com.example.demo.dto.ProductInfo;
import com.example.demo.enumy.CartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CART")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "cart_product_ids", joinColumns = @JoinColumn(name = "cart_id"))
    @Column(name = "product_id")
    private List<ProductInfo> productIds = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    private LocalDateTime creationTime;
    private BigDecimal totalValue;
}
