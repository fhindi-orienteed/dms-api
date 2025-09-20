package com.dms.base.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dms.base.dto.response.mobile.MobileLoginResponse;
import com.dms.base.dto.response.web.WebLoginResponse;
import com.dms.base.exception.AccountLockedException;
import com.dms.base.exception.InvalidCredentialsException;
import com.dms.base.mapper.UserMapper;
import com.dms.base.model.User;
import com.dms.base.repository.UserRepository;
import com.dms.base.util.Constant;
import com.dms.base.util.JwtUtility;

@Service
public class AuthService {

    @Value("${jwt.web.expiration}")
    private long webExpiration;

    @Autowired
    private UserMapper userMapper;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtility jwtUtility;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtility jwtUtility) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtility = jwtUtility;
    }

    public WebLoginResponse webLogin(String userName, String password) {
        User user = userRepository.findByUserName(userName).orElseThrow(InvalidCredentialsException::new);

        if (Constant.USER_STATUS_DISABLED == user.getStatus()) {
            throw new AccountLockedException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }
        String accessToken = jwtUtility.generateWebAccessToken(user);

        WebLoginResponse response = new WebLoginResponse(accessToken, userMapper.mapToWebResponse(user));
        updateLastSession(user);
        response.setExpiresIn(webExpiration);

        return response;
    }

    public MobileLoginResponse mobileLogin(String userName, String password) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(InvalidCredentialsException::new);

        if (Constant.USER_STATUS_DISABLED == user.getStatus()) {
            throw new AccountLockedException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtUtility.generateMobileAccessToken(user);
        updateLastSession(user);
        MobileLoginResponse response = new MobileLoginResponse(accessToken, userMapper.mapToMobileResponse(user));
        return response;
    }

    private void updateLastSession(User user){
        user.setLastSession(LocalDateTime.now());
        userRepository.save(user);
    }
}
