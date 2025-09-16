package com.dms.base.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dms.base.config.CustomUserDetails;
import com.dms.base.model.User;
import com.dms.base.repository.UserRepository;
import com.dms.base.util.Constant.RoleType;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        if (RoleType.ROLE_COMPANY_ADMIN.toString().equals(user.getRole())
                || RoleType.ROLE_COMPANY_USER.toString().equals(user.getRole())) {
            // TODO: fetch company id
            return new CustomUserDetails(user);
        }
        return new CustomUserDetails(user);
    }
}
