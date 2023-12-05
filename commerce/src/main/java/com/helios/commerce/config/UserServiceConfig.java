package com.helios.commerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.helios.commerce.model.User;
import com.helios.commerce.repositories.UserRepository;

public class UserServiceConfig implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find username");
        }
        return new UserDetails(user);
    }
}
