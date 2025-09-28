package com.example.TalentGo.service;

import com.example.TalentGo.entity.User;
import com.example.TalentGo.enums.UserRole;
import com.example.TalentGo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        // Password encode karo
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role set karo (signup ke time role bhejne ki zaroorat nahi)
        return userRepository.save(user);
    }

}

