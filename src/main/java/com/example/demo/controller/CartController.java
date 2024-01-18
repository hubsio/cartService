package com.example.demo.controller;

import com.example.demo.model.entity.Cart;
import com.example.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/create")
    public Cart createCart() {
        return cartService.createCart();
    }

    @PutMapping("/{cartId}/addProduct/{productId}")
    public Cart addProductToCart(@PathVariable Long cartId, @PathVariable Long productId) {
        return cartService.addProductToCart(cartId, productId);
    }

    @PutMapping("/{cartId}/addAccessory/{accessoryId}")
    public Cart addAccessoryToCart(@PathVariable Long cartId, @PathVariable Long accessoryId) {
        return cartService.addAccessoryToCart(cartId, accessoryId);
    }

    @GetMapping("/{cartId}")
    public Cart viewCart(@PathVariable Long cartId) {
        return cartService.viewCart(cartId);
    }
}
