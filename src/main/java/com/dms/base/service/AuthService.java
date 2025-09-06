package com.dms.base.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dms.base.dto.response.LoginResponse;
import com.dms.base.exception.AccountLockedException;
import com.dms.base.exception.InvalidCredentialsException;
import com.dms.base.mapper.UserMapper;
import com.dms.base.model.User;
import com.dms.base.repository.UserRepository;
import com.dms.base.util.JwtUtility;

@Service
public class AuthService implements UserDetailsService {

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

    public LoginResponse webLogin(String userName, String password) {
        User user = userRepository.findByUserName(userName).orElseThrow(InvalidCredentialsException::new);

        if (user.isLocked()) {
            throw new AccountLockedException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtUtility.generateWebAccessToken(user);

        LoginResponse response = new LoginResponse(accessToken, userMapper.mapUserLoginResponse(user));

        response.setExpiresIn(webExpiration);
        return response;
    }

    public LoginResponse mobileLogin(String userName, String password) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(InvalidCredentialsException::new);

        if (user.isLocked()) {
            throw new AccountLockedException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtUtility.generateMobileAccessToken(user);

        return new LoginResponse(accessToken, userMapper.mapUserLoginResponse(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getStatus(),
                true,
                true,
                true,
                Collections.singleton(getAuthority(user)));
    }

    private GrantedAuthority getAuthority(User user) {
        String roleName = "ROLE_" + user.getRole();
        return new SimpleGrantedAuthority(roleName);
    }
}
