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
import com.dms.base.dto.request.mobile.MobileRegisterRequest;
import com.dms.base.dto.response.mobile.MobileRegisterResponse;
import com.dms.base.dto.response.web.WebUserResponse;
import com.dms.base.exception.UserAlreadyExistsException;

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

    public MobileRegisterResponse registerNewMobileUser(MobileRegisterRequest request) {
    
    if (userRepository.existsByEmail(request.getEmail())) {
        throw new UserAlreadyExistsException("A user with this email already exists: " + request.getEmail());
    }
    
    User newUser = new User();
    newUser.setName(request.getName());
    newUser.setEmail(request.getEmail());
    newUser.setPhone(request.getPhone());

    newUser.setPassword(passwordEncoder.encode(request.getPassword()));

    newUser.setRole("USER"); 
    newUser.setCreatedDate(LocalDateTime.now());
    newUser.setStatus(Constant.USER_STATUS_ENEABLED);
    newUser.setLocked(false);
    newUser.setPasswordRetries(0);
    newUser.setPasswordExpired(false);

    User savedUser = userRepository.save(newUser);

    MobileRegisterResponse response = new MobileRegisterResponse();
    response.setId(savedUser.getId());
    response.setName(savedUser.getName());
    response.setEmail(savedUser.getEmail());
    response.setPhone(savedUser.getPhone());
    
    return response;
   }

    public MobileRegisterResponse registerNewMobileMerchantUser(String name, String email, String phone, String password) {
    
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("A user with this email already exists: " + email);
        }
        
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPhone(phone);

        newUser.setPassword(passwordEncoder.encode(password));

        newUser.setRole("ROLE_ADMIN"); 
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setStatus(Constant.USER_STATUS_ENEABLED);
        newUser.setLocked(false);
        newUser.setPasswordRetries(0);
        newUser.setPasswordExpired(false);

        User savedUser = userRepository.save(newUser);

        MobileRegisterResponse response = new MobileRegisterResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setPhone(savedUser.getPhone());
        
        return response;
    }


    public WebUserResponse createNewCompanyUser(String name, String email, String phone, String password,
            String role) {

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("A user with this email already exists: " + email);
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPhone(phone);

        newUser.setPassword(passwordEncoder.encode(password));

        newUser.setRole(role);
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setStatus(Constant.USER_STATUS_ENEABLED);
        newUser.setLocked(false);
        newUser.setPasswordRetries(0);
        newUser.setPasswordExpired(false);

        User savedUser = userRepository.save(newUser);

        WebUserResponse response = new WebUserResponse();
        response.setId(savedUser.getId());
        response.setCreatedDate(savedUser.getCreatedDate());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setPhone(savedUser.getPhone());
        response.setStatus(savedUser.getStatus());
        response.setRole(savedUser.getRole());
        response.setLastSession(savedUser.getLastSession());
        return response;
    }
}