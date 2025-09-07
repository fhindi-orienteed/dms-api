package com.dms.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dms.base.model.User;
import com.dms.base.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User createNewUser(User newUser){
        return userRepository.save(newUser);
    }
}