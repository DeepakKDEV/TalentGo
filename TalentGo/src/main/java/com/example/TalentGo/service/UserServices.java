//package com.example.TalentGo.service;
//
//import com.example.TalentGo.entity.User;
//import com.example.TalentGo.enums.UserRole;
//import com.example.TalentGo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServices {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public User createUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Default role if not provided
//        if (user.getRole() == null) {
//            user.setRole(UserRole.USER);
//        }
//
//        return userRepository.save(user);
//    }
//}
