package com.dhruv.ms.authService.config;

import com.dhruv.ms.authService.model.UserCredential;
import com.dhruv.ms.authService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUsersDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> credential = userRepository.findByUsername(username);
        return credential.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
