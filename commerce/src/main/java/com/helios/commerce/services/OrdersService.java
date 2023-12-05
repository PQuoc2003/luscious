package com.helios.commerce.services;

import com.helios.commerce.model.Orders;
import org.springframework.stereotype.Service;

@Service
public interface OrdersService {

    Orders addOrder(String phone , String address,double total,String username);

    void updateOrders(Orders orders);

    Iterable<Orders> findByStatus();
    Iterable<Orders> findByUsername(String username);

    Iterable<Orders> findOrdersById(Long id);

    void deleteById(Long id);


}
