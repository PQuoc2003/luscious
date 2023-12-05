package com.helios.commerce.controller;

import com.helios.commerce.model.Product;
import com.helios.commerce.services.CartService;
import com.helios.commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SearchController {

    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public SearchController(ProductService productService, CartService cartService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping(value = "/search" , method = RequestMethod.POST)
    public String getSearchResult(@RequestParam(value = "search_home") String keyword, Model model, Principal principal){
        init(model,principal);
        Iterable<Product> products = productService.searchProduct(keyword);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String role = authentication.getAuthorities().toString();

        model.addAttribute("role", role);

        model.addAttribute("products", products);

        return "search_page";
    }

    public void init(Model model, Principal principal){
        if (principal != null) {
            model.addAttribute("user","logined");
            model.addAttribute("totalPrice", cartService.getTotalPrice(principal.getName()));
            model.addAttribute("cartItems", cartService.getCart(principal.getName()));
        }
    }
}
