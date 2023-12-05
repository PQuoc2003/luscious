package com.helios.commerce.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.helios.commerce.dto.UserDTO;
import com.helios.commerce.model.Role;
import com.helios.commerce.model.User;
import com.helios.commerce.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Username or password invalid!");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/do-register")
    public String registerCustomer(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                                   BindingResult result,
                                   Model model) {
        try {
            System.out.println("1");
            if (result.hasErrors()) {
                System.out.println(2);
                model.addAttribute("userDTO",new UserDTO());
                return "register";
            } else if (userDTO.getPassword().isEmpty() || userDTO.getUsername().isEmpty()) {
                model.addAttribute("error", "Please enter sufficient information!");
                return "register";
            } else if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                model.addAttribute("error", "Confirm incorrect password!");
                return "register";
            }else if (userDTO.getPassword().length() > 30 || userDTO.getPassword().length() < 6) {
                model.addAttribute("error", "Valid passwords between 6-30!");
                return "register";
            } else if (userDTO.getUsername().length() > 30 || userDTO.getUsername().length() < 6) {
                model.addAttribute("error", "Valid username between 6-30!");
                return "register";
            }
            else {
                System.out.println(3);
                String username = userDTO.getUsername();
                User checkExist = userService.findByUsername(username);
                if (checkExist != null) {
                    System.out.println(4);
                    model.addAttribute("error", "Username already exists!");
                    return "register";
                }
                System.out.println(5);
                userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                userDTO.setRole("user");
                userService.save(userDTO);
                model.addAttribute("success", "Register successfully!");
            }
        } catch (Exception e) {
            System.out.println(6);
            e.printStackTrace();
            model.addAttribute("error", "Server is error, try again later!");
        }
        System.out.println(7);
        return "login";
    }
}
