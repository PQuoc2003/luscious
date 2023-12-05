package com.helios.commerce.repositories;

import com.helios.commerce.model.Cart;
import com.helios.commerce.model.Product;
import com.helios.commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserAndStatus(User user, boolean status);

    List<Cart> findByProduct(Product product);

    void deleteById(Long id);

    Cart findAllByUserAndProduct(User user,Product product);

}
