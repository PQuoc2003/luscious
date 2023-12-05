package com.helios.commerce.repositories;

import com.helios.commerce.model.OrderDetail;
import com.helios.commerce.model.Orders;
import com.helios.commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    Iterable<OrderDetail> findByProduct(Product product);

    void deleteById(Long id);

    List<OrderDetail> findAllByProduct(Product product);

    Iterable<OrderDetail> findAllByOrders(Orders orders);

}
