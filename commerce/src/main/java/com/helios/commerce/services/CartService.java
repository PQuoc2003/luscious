package com.helios.commerce.services;

import com.helios.commerce.dto.AddToCartDTO;
import com.helios.commerce.model.Cart;
import com.helios.commerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    void addToCart(AddToCartDTO addToCartDTO);

    List<Cart> getCart(String username);

    void removeCart(Long id);

    void deleteAllByUser(String username);

    List<Cart> findAllByProduct(Product product);

    void deleteById(Long id);

    Long getTotalPrice(String username);


}
