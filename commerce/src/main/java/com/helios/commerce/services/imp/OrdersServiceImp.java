package com.helios.commerce.services.imp;

import com.helios.commerce.model.Orders;
import com.helios.commerce.model.User;
import com.helios.commerce.repositories.OrdersRepository;
import com.helios.commerce.services.OrdersService;
import com.helios.commerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImp implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserService userService;

    @Autowired
    public OrdersServiceImp(OrdersRepository ordersRepository, UserService userService) {
        this.ordersRepository = ordersRepository;
        this.userService = userService;
    }

    @Override
    public Orders addOrder(String phone, String address,double total,String username) {


        User user = userService.findByUsername(username);

        Orders orders = new Orders(user, phone , address, false, total);

        ordersRepository.save(orders);

        return orders;
    }

    @Override
    public void updateOrders(Orders orders) {
        ordersRepository.save(orders);
    }

    @Override
    public Iterable<Orders> findByStatus() {
        return ordersRepository.findByStatus(false);
    }

    @Override
    public Iterable<Orders> findByUsername(String username) {
        return ordersRepository.findByUser(userService.findByUsername(username));
    }

    @Override
    public Iterable<Orders> findOrdersById(Long id) {
        return ordersRepository.findAllById(id);
    }

    @Override
    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }
}
