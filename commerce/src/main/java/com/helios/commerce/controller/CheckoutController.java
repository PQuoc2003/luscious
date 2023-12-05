package com.helios.commerce.controller;

import com.helios.commerce.model.Cart;
import com.helios.commerce.model.Orders;
import com.helios.commerce.model.OrderDetail;
import com.helios.commerce.services.CartService;
import com.helios.commerce.services.OrderDetailService;
import com.helios.commerce.services.OrdersService;
import com.helios.commerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class CheckoutController {

    private  final CartService cartService;

    private  final OrdersService ordersService;

    private final OrderDetailService orderDetailService;

    private final UserService userService;

    private Iterable<Cart> carts;
    private double totalPrice = 0;

    @Autowired
    public CheckoutController(CartService cartService, OrdersService ordersService, OrderDetailService orderDetailService, UserService userService) {
        this.cartService = cartService;
        this.ordersService = ordersService;
        this.orderDetailService = orderDetailService;
        this.userService = userService;
    }

    @RequestMapping(value = "/checkout")
    public String getCheckoutPage(Model model, Principal principal){
        init(model, principal);
        carts = cartService.getCart(principal.getName());

        totalPrice = 0;


        totalPrice = cartService.getTotalPrice(principal.getName());

        model.addAttribute("carts", carts);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();

        model.addAttribute("role", role);

        return "checkout";
    }

    @RequestMapping(value = "/checkout/success")
    public String getCheckoutSuccess(
            @RequestParam("phone") int phone,
            @RequestParam("address") String address,
            Model model,
            Principal principal){
        init(model, principal);
        if(carts.iterator().hasNext()){

            Orders orders = ordersService.addOrder(String.valueOf(phone), address,totalPrice,principal.getName());

            for (Cart cart: carts) {
                OrderDetail orderDetail = new OrderDetail(orders, cart.getProduct(),cart.getQuantity());
                orderDetailService.addOrderDetail(orderDetail);
            }




            cartService.deleteAllByUser(principal.getName());

            model.addAttribute("phone", String.valueOf(phone));
            model.addAttribute("address", address);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String role = authentication.getAuthorities().toString();

            model.addAttribute("role", role);

            return "checkout_success";

        }

        return "redirect:/home";
    }
    public void init(Model model, Principal principal){
        if (principal != null) {
            model.addAttribute("user","logined");
            model.addAttribute("totalPrice", cartService.getTotalPrice(principal.getName()));
            model.addAttribute("cartItems", cartService.getCart(principal.getName()));
        }
    }
}
