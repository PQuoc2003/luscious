package com.helios.commerce.controller;

import com.helios.commerce.model.OrderDetail;
import com.helios.commerce.model.Orders;
import com.helios.commerce.services.CartService;
import com.helios.commerce.services.OrderDetailService;
import com.helios.commerce.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
@Controller
public class OrderController {
    private final OrdersService ordersService;
    private final CartService cartService;
    private final OrderDetailService orderDetailService;
    @Autowired
    public OrderController(OrdersService ordersService, CartService cartService,OrderDetailService orderDetailService) {
        this.ordersService = ordersService;
        this.cartService = cartService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping(value = "/order")
    public String getMenu(Model model, Principal principal){
        init(model, principal);
        Iterable<Orders> orders = ordersService.findByUsername(principal.getName());
        model.addAttribute("orders", orders);

        return "order_customer";
    }

    @GetMapping(value = "/order/orderdetail/{order_id}")
    public String detailOrder(@PathVariable String order_id, Model model, Principal principal){
        init(model,principal);

        Iterable<Orders> orders = ordersService.findOrdersById(Long.parseLong(order_id));

        if(!orders.iterator().hasNext()){
            return "redirect:/order";
        }

        Orders order = orders.iterator().next();

        Iterable<OrderDetail> orderDetails = orderDetailService.findByOrders(order);

        model.addAttribute("orderDetails", orderDetails);

        model.addAttribute("order", order);


        return "order_detail";
    }

    @GetMapping(value = "/order/orderapprove/{order_id}")
    public String approveOrder(@PathVariable String order_id){

        Iterable<Orders> orders = ordersService.findOrdersById(Long.parseLong(order_id));

        if(orders.iterator().hasNext()){
            Orders order = orders.iterator().next();

            order.setStatus(true);

            ordersService.updateOrders(order);
        }



        return "redirect:/order";
    }

    public void init(Model model, Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();
        model.addAttribute("role", role);
        if (principal != null) {
            model.addAttribute("user","logined");
            model.addAttribute("totalPrice", cartService.getTotalPrice(principal.getName()));
            model.addAttribute("cartItems", cartService.getCart(principal.getName()));
        }
    }
}
