package com.helios.commerce.controller;

import com.helios.commerce.dto.AddToCartDTO;
import com.helios.commerce.model.Cart;
import com.helios.commerce.model.Product;
import com.helios.commerce.services.CartService;
import com.helios.commerce.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    CartService cartService;
    ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @RequestMapping(value = "/cart/{product_id}")
    public String add(@PathVariable String product_id, Principal principal, HttpServletRequest request){

        AddToCartDTO addToCartDTO = new AddToCartDTO();
        addToCartDTO.setProductId(Long.valueOf(product_id));
        addToCartDTO.setUsername(principal.getName());
        addToCartDTO.setQuantity(1L);

        cartService.addToCart(addToCartDTO);

        String referer = request.getHeader("Referer");
        if (referer != null && !referer.isEmpty()) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/cart")
    public String getCartPage(Model model,Principal principal){
        init(model, principal);
        Iterable<Cart> carts = cartService.getCart(principal.getName());

        List<Product> products = new ArrayList<Product>();

        for (Cart cart: carts) {
            products.add(cart.getProduct());
        }
        model.addAttribute("products", products);
        model.addAttribute("carts", carts);


        return "cart";
    }

    @RequestMapping(value = "/cart/remove/{cart_id}")
    public String removeCart(@PathVariable String cart_id){


        cartService.removeCart(Long.parseLong(cart_id));

        return "redirect:/cart";
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
