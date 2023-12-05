package com.helios.commerce.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import com.helios.commerce.dto.UserDTO;
import com.helios.commerce.model.Role;
import com.helios.commerce.model.User;
import com.helios.commerce.services.UserService;

@RequiredArgsConstructor
@Controller
public class AdminConfiguration {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdmin() {
        try {
            User admin = userService.findByUsername("admin");
            if (admin == null) {
                UserDTO adminDTO = new UserDTO();
                adminDTO.setUsername("admin");
                adminDTO.setPassword("admin123");
                adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
                adminDTO.setRole("admin");
                userService.save(adminDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
