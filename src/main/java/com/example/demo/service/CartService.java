package com.example.demo.service;

import com.example.demo.client.ProductServiceFeignClient;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartProduct;
import com.example.demo.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductServiceFeignClient productServiceFeignClient;

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Long productID = productServiceFeignClient.getProductInfo(productId);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setId(productID);

        cart.getCartProducts().add(cartProduct);
        return cartRepository.save(cart);
    }

    public Cart viewCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}
