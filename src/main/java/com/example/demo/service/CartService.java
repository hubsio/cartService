package com.example.demo.service;

import com.example.demo.client.ProductServiceFeignClient;
import com.example.demo.enumy.CartStatus;
import com.example.demo.exception.CartModificationTimeExpiredException;
import com.example.demo.exception.CartNotFoundException;
import com.example.demo.exception.CompletedCartModificationException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.entity.Cart;
import com.example.demo.dto.ProductInfo;
import com.example.demo.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final ProductServiceFeignClient productServiceFeignClient;

    public Cart createCart() {
        Cart cart = new Cart();
        cart.setCreationTime(LocalDateTime.now());
        cart.setStatus(CartStatus.IN_PROGRESS);
        log.info("Created new cart with ID: {}", cart.getId());
        return cartRepository.save(cart);
    }

    @Transactional
    public ProductInfo addProductToCart(Long cartId, Long productId) {
        log.info("Adding product with ID {} to cart with ID {}", productId, cartId);
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        validateCartStatus(cart);
        Optional<ProductInfo> productInfo = productServiceFeignClient.getProductInfo(productId);

        if (productInfo.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        ProductInfo product = productInfo.get();
        Long productID = product.getId();
        if (cart.getProductIds().stream().anyMatch(p -> p.getId().equals(productID))) {
            throw new RuntimeException("Product already added to the cart");
        }

        cart.getProductIds().add(product);
        updateCartTotalValue(cart);
        log.info("Product with ID {} successfully added to the cart with ID {}", productId, cartId);
        cartRepository.save(cart);

        return product;
    }

    private void updateCartTotalValue(Cart cart) {
        BigDecimal totalValue = cart.getProductIds().stream()
                .map(ProductInfo::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalValue(totalValue);
    }

    public Cart viewCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
    }

    private void validateCartStatus(Cart cart) {
        if (cart.getStatus() == CartStatus.COMPLETED) {
            throw new CompletedCartModificationException("Cannot modify a completed cart");
        }

        LocalDateTime expirationTime = cart.getCreationTime().plusMinutes(30);
        if (LocalDateTime.now().isAfter(expirationTime)) {
            throw new CartModificationTimeExpiredException("Cart modification time expired");
        }
    }
}
