package com.dms.base.service;

import com.dms.base.dto.UserDto;
import com.dms.base.dto.request.LoginRequest;
import com.dms.base.dto.response.LoginResponse;
import com.dms.base.exception.AccountLockedException;
import com.dms.base.exception.InvalidCredentialsException;
import com.dms.base.model.User;
import com.dms.base.repository.UserRepository;
import com.dms.base.util.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (user.isLocked()) {
            throw new AccountLockedException();
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponse(
                accessToken,
                refreshToken,
                3600,
                new UserDto(user.getId().toString(), user.getName(), user.getEmail(),
                        user.getPhone(), user.getRole())
        );
    }
}
