package com.example.demo.model.entity;

import com.example.demo.enumy.CartStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cart")
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
    private List<Long> productIds = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "cart_accessory_ids", joinColumns = @JoinColumn(name = "cart_id"))
    @Column(name = "accessory_id")
    private List<Long> accessoryIds = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    private LocalDateTime creationTime;
}
