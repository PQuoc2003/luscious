package com.helios.commerce.controller;

import com.helios.commerce.model.Product;
import com.helios.commerce.model.Type;
import com.helios.commerce.services.CartService;
import com.helios.commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {


    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public HomeController(ProductService productService, CartService cartService) {
        this.cartService = cartService;
        this.productService = productService;
    }



    @RequestMapping(value = {"/", "/home"})
    public String getHomepage(Model model, Principal principal){
        init(model, principal);
        Iterable<Product> products = productService.findAllPaging(0,6);

        model.addAttribute("products", products);

        return "home";
    }




    @RequestMapping(value = "/menu")
    public String getMenu(Model model, Principal principal){
        init(model, principal);
        Iterable<Product> pizza_products = productService.findByType(Type.PIZZA);
        Iterable<Product> burger_products = productService.findByType(Type.BURGER);
        Iterable<Product> drink_products = productService.findByType(Type.DRINK);

        model.addAttribute("pizza_products", pizza_products);
        model.addAttribute("burger_products", burger_products);
        model.addAttribute("drink_products", drink_products);
        return "menu";
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
