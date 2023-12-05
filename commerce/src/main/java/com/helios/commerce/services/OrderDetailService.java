package com.helios.commerce.services;

import com.helios.commerce.model.OrderDetail;
import com.helios.commerce.model.Orders;
import com.helios.commerce.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {

    void addOrderDetail(OrderDetail orderDetail);

    Iterable<OrderDetail> findAllByProduct(Product product);

    void deleteById(Long id);

    int countByProduct(Product product);

    Iterable<OrderDetail> findByOrders(Orders orders);



}
