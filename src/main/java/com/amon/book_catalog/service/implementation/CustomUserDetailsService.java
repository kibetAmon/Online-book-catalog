package com.amon.book_catalog.service.implementation;

// Local libraries
import com.amon.book_catalog.entities.User;
import com.amon.book_catalog.repository.UserRepository;

// Global libraries
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Load user by username
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(usernameOrEmail)
                .or(() -> userRepository.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),  // or user.getUsername() based on how you authenticate
                user.getPassword(),
                Collections.singleton(user.getRole() == User.Role.ADMIN ?
                        new SimpleGrantedAuthority("ROLE_ADMIN") :
                        new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
