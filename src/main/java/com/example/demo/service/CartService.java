package com.example.demo.service;

import com.example.demo.client.ProductServiceFeignClient;
import com.example.demo.model.entity.Cart;
import com.example.demo.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final ProductServiceFeignClient productServiceFeignClient;

    public Cart createCart() {
        Cart cart = new Cart();
        log.info("Created new cart with ID: {}", cart.getId());
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart addProductToCart(Long cartId, Long productId) {
        log.info("Adding product with ID {} to cart with ID {}", productId, cartId);
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Optional<Long> productInfo = productServiceFeignClient.getProductInfo(productId);

        if (productInfo.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        Long productID = productInfo.get();

        if (cart.getProductIds().contains(productID)) {
            throw new RuntimeException("Product already added to the cart");
        }

        cart.getProductIds().add(productID);
        log.info("Product with ID {} successfully added to the cart with ID {}", productId, cartId);
        return cartRepository.save(cart);
    }

    public Cart viewCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
