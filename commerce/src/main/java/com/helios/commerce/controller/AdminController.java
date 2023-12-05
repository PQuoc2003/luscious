package com.helios.commerce.controller;

import com.helios.commerce.dao.RevenueDAO;
import com.helios.commerce.model.*;
import com.helios.commerce.services.CartService;
import com.helios.commerce.services.OrderDetailService;
import com.helios.commerce.services.OrdersService;
import com.helios.commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminController {

    private final ProductService productService;

    private final OrderDetailService orderDetailService;

    private final CartService cartService;

    private final OrdersService ordersService;

    @Autowired
    public AdminController(ProductService productService, OrderDetailService orderDetailService, CartService cartService, OrdersService ordersService) {
        this.productService = productService;
        this.orderDetailService = orderDetailService;
        this.cartService = cartService;
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/admin")
    public String getHomePage(Model model, Principal principal){
        init(model,principal);
        return "admin_manage";
    }


    @RequestMapping(value = "/admin/add")
    public String getAddPage(Model model, Principal principal){
        init(model,principal);
        return "add_product";
    }

    @RequestMapping(value = "/admin/add/success" , method = RequestMethod.POST)
    public String addProduct(
            @RequestParam("typesOfPro") String types,
            @RequestParam("name") String name,
            @RequestParam("des") String des,
            @RequestParam("price") double price
    ){

        Type typeOfProduct = switch (types) {
            case "pizza" -> Type.PIZZA;
            case "burger" -> Type.BURGER;
            default -> Type.DRINK;
        };

        Product product = new Product(name, price, des, typeOfProduct);

        productService.addProduct(product);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/manage")
    public String getManagePage(Model model, Principal principal){

        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);

        init(model,principal);
        return "manage_product";
    }

    @RequestMapping(value = "/admin/remove/{product_id}")
    public String removeProduct(@PathVariable String product_id){
       Iterable<Product> products =  productService.findProductById(Long.parseLong(product_id));
       if(!products.iterator().hasNext()){
           return "redirect:/admin/manage";
       }
        Product product = products.iterator().next();


        Iterable<OrderDetail> orderDetails = orderDetailService.findAllByProduct(product);

        for (OrderDetail orderDetail: orderDetails) {
            orderDetailService.deleteById(orderDetail.getId());
        }

        List<Cart> carts = cartService.findAllByProduct(product);

        for (Cart cart: carts) {
            cartService.deleteById(cart.getId());
        }




        productService.deleteById(product.getId());


        return "redirect:/admin/manage";
    }


    @RequestMapping(value = "/admin/edit/{product_id}")
    public String editProduct(@PathVariable String product_id , Model model, Principal principal){

        Iterable<Product> products =  productService.findProductById(Long.parseLong(product_id));

        if(!products.iterator().hasNext()){
            return "redirect:/admin/manage";
        }

        Product product = products.iterator().next();

        model.addAttribute("product", product);

        init(model,principal);
        return "update_product";


    }

    @RequestMapping(value = "/admin/update/{product_id}")
    public String updateProduct(
            @PathVariable String product_id,
            @RequestParam("typesOfPro") String types,
            @RequestParam("name") String name,
            @RequestParam("des") String des,
            @RequestParam("price") double price
            ){


        Type typeOfProduct = switch (types) {
            case "pizza" -> Type.PIZZA;
            case "burger" -> Type.BURGER;
            default -> Type.DRINK;
        };

        Product product = new Product(Long.parseLong(product_id), name, price, des, typeOfProduct);

        productService.addProduct(product);

        return "redirect:/admin/manage";


    }

    @RequestMapping(value = "/admin/revenue")
    public String getRevenue(Model model, Principal principal){
        init(model,principal);

        Iterable<Product> products = productService.findAll();

        List<RevenueDAO> revenueDAOList = new ArrayList<>();

        double totalRevenue = 0;

        for(Product product : products){

            int quantity = orderDetailService.countByProduct(product);

            RevenueDAO revenueDAO = new RevenueDAO(product, quantity, product.getPrice()*quantity);

            revenueDAOList.add(revenueDAO);

            totalRevenue += product.getPrice()*quantity;

        }

        model.addAttribute("revenues", revenueDAOList);
        model.addAttribute("totalRevenue", totalRevenue);

        return "revenue";
    }


    @RequestMapping(value = "/admin/orders")
    public String getOrders(Model model, Principal principal){
        init(model,principal);

        Iterable<Orders> orders = ordersService.findByStatus();

        model.addAttribute("orders", orders);

        return "admin_order";
    }

    @RequestMapping(value = "/admin/orderapprove/{order_id}")
    public String approveOrder(@PathVariable String order_id){

        Iterable<Orders> orders = ordersService.findOrdersById(Long.parseLong(order_id));

        if(orders.iterator().hasNext()){
            Orders order = orders.iterator().next();

            order.setStatus(true);

            ordersService.updateOrders(order);
        }



        return "redirect:/admin/orders";
    }

    @RequestMapping(value = "/admin/orderremove/{order_id}")
    public String removeOrder(@PathVariable String order_id){
        Iterable<Orders> orders = ordersService.findOrdersById(Long.parseLong(order_id));

        if(!orders.iterator().hasNext()){
            return "redirect:/admin/orders";
        }

        Orders order = orders.iterator().next();

        Iterable<OrderDetail> orderDetails = orderDetailService.findByOrders(order);

        for(OrderDetail orderDetail : orderDetails){
            orderDetailService.deleteById(orderDetail.getId());
        }

        ordersService.deleteById(order.getId());

        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/orderdetail/{order_id}")
    public String detailOrder(@PathVariable String order_id, Model model, Principal principal){
        init(model,principal);

        Iterable<Orders> orders = ordersService.findOrdersById(Long.parseLong(order_id));

        if(!orders.iterator().hasNext()){
            return "redirect:/admin/orders";
        }

        Orders order = orders.iterator().next();

        Iterable<OrderDetail> orderDetails = orderDetailService.findByOrders(order);


        model.addAttribute("orderDetails", orderDetails);

        model.addAttribute("order", order);


        return "order_detail";
    }

    public void init(Model model, Principal principal){
        if (principal != null) {
            model.addAttribute("user","logined");
            model.addAttribute("totalPrice", cartService.getTotalPrice(principal.getName()));
            model.addAttribute("cartItems", cartService.getCart(principal.getName()));
        }
    }
}
