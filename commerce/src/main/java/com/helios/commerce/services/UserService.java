package com.helios.commerce.services;

import com.helios.commerce.dto.UserDTO;
import com.helios.commerce.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findByUsername(String username);

    int register(User user);

    List<User> findAll();

    User save(UserDTO userDTO);
}
