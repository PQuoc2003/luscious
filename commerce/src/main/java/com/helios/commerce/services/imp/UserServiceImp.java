package com.helios.commerce.services.imp;

import com.helios.commerce.dto.UserDTO;
import com.helios.commerce.model.Role;
import com.helios.commerce.model.User;
import com.helios.commerce.repositories.UserRepository;
import com.helios.commerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public int register(User user) {
        User users = userRepository.findByUsername(user.getUsername());

        if (users == null){
            userRepository.save(user);
            return 1;
        }
        return 0;


    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll(PageRequest.of(0,1)).getContent();
    }

    @Override
    public User save(UserDTO userDTO) {
        User user = new User();

        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        if (userDTO.getRole().equals("admin")) {
            user.setRole(Role.ROLE_ADMIN);
        } else {
            user.setRole(Role.ROLE_USER);
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getRole().name());
        return userRepository.save(user);
    }
}
