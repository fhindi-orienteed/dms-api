package com.dms.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Date;
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

    public User createNewUser(String username, String password, String role, Date createdDate, 
                            Integer status, Integer passwordRetries, Integer passwordExpired, 
                            Integer passwordCreation, Integer timeout) {
        User newUser = new User();
        newUser.setUserName(username);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setCreatedDate(createdDate);
        newUser.setStatus(status);
        newUser.setPasswordRetries(passwordRetries);
        newUser.setPasswordExpired(passwordExpired);
        newUser.setPasswordCreation(passwordCreation);
        newUser.setTimeout(timeout);
        
        return userRepository.save(newUser);
    }
}