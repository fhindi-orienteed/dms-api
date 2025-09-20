package com.dms.base.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dms.base.model.User;
import com.dms.base.repository.UserRepository;
import com.dms.base.util.Constant;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return this.getByUserName(authentication.getName());
        }
        return null; // No authenticated user or user details found
    }

    public User createNewUser(String username, String password, String role) {

        User newUser = new User();

        String hashedPassword = passwordEncoder.encode(password);

        newUser.setUserName(username);
        newUser.setPassword(hashedPassword);
        newUser.setRole(role);
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setStatus(Constant.USER_STATUS_ENEABLED);
        newUser.setPasswordRetries(0);
        newUser.setPasswordExpired(false);
        newUser.setPasswordCreation(LocalDateTime.now());
        newUser.setTimeout(0);

        return userRepository.save(newUser);
    }
}