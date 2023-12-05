package com.helios.commerce.services.imp;

import com.helios.commerce.model.OrderDetail;
import com.helios.commerce.model.Orders;
import com.helios.commerce.model.Product;
import com.helios.commerce.repositories.OrderDetailRepository;
import com.helios.commerce.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImp implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImp(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public Iterable<OrderDetail> findAllByProduct(Product product) {
        return orderDetailRepository.findByProduct(product);
    }

    @Override
    public void deleteById(Long id) {
        orderDetailRepository.deleteById(id);
    }


    @Override
    public int countByProduct(Product product) {
        return orderDetailRepository.findAllByProduct(product)
                .stream()
                .mapToInt(OrderDetail::getQuantity)
                .sum();
    }

    @Override
    public Iterable<OrderDetail> findByOrders(Orders orders) {
        return orderDetailRepository.findAllByOrders(orders);
    }


}
