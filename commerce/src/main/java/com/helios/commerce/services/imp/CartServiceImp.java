package com.helios.commerce.services.imp;

import com.helios.commerce.dto.AddToCartDTO;
import com.helios.commerce.model.Cart;
import com.helios.commerce.model.Product;
import com.helios.commerce.model.User;
import com.helios.commerce.repositories.CartRepository;
import com.helios.commerce.repositories.ProductRepository;
import com.helios.commerce.services.CartService;
import com.helios.commerce.services.ProductService;
import com.helios.commerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CartServiceImp implements CartService {

    CartRepository cartRepository;
    UserService userService;
    ProductService productService;

    @Autowired
    public CartServiceImp(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void addToCart(AddToCartDTO addToCartDTO) {
        Iterable<Product> product = productService.findProductById(addToCartDTO.getProductId());

        User user = userService.findByUsername(addToCartDTO.getUsername());

        Cart cartItem = cartRepository.findAllByUserAndProduct(user,product.iterator().next());

        if(cartItem == null){
            cartItem = new Cart();
        }
        cartItem.setProduct(product.iterator().next());

        cartItem.setQuantity(cartItem.getQuantity() + Math.toIntExact(addToCartDTO.getQuantity()));

        cartItem.setUser(user);

        cartRepository.save(cartItem);
    }

    @Override
    public List<Cart> getCart(String username) {
        return cartRepository.findByUserAndStatus(userService.findByUsername(username), false);
    }

    @Override
    public void removeCart(Long id) {
        cartRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public void deleteAllByUser(String username) {
        List<Cart> carts = this.getCart(username);

        for(Cart cart : carts){
            removeCart(cart.getId());
        }

    }

    @Override
    public List<Cart> findAllByProduct(Product product) {
        return cartRepository.findByProduct(product);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Long getTotalPrice(String username) {
        List<Cart> cartItemList = cartRepository.findByUserAndStatus(userService.findByUsername(username),false);
        return cartItemList
                .stream()
                .mapToLong(cartItem -> (long) (cartItem.getQuantity() * cartItem.getProduct().getPrice()))
                .sum();
    }


}
