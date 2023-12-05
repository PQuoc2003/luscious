package com.helios.commerce.repositories;

import com.helios.commerce.model.Orders;
import com.helios.commerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Iterable<Orders> findByStatus(boolean status);

    Iterable<Orders> findAllById(Long id);

    void deleteById(Long id);

    Iterable<Orders> findByUser(User user);

}
