package com.dhruv.ms.authService.service;

import com.dhruv.ms.authService.dto.UserDto;
import com.dhruv.ms.authService.dto.UserResponse;
import com.dhruv.ms.authService.model.DeliveryAgent;
import com.dhruv.ms.authService.model.UserCredential;
import com.dhruv.ms.authService.model.UserRole;
import com.dhruv.ms.authService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        userRepository.save(credential);
        return "User added to the System";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public UserResponse getUser(Long id) {
        Optional<UserCredential> user = userRepository.findById(id);
        if(user.isPresent()) {
            UserCredential user1 = user.get();
            return mapUserToUserResponse(user1);
        }
        return UserResponse.builder()
                .responseCode(404)
                .msg("User with given id is not present")
                .build();
    }

    public UserResponse updateUser(UserCredential user) {
        if(user.getId() == null) {
            return UserResponse.builder()
                    .responseCode(400)
                    .msg("Please provide user id")
                    .build();
        }
        Optional<UserCredential> userOptional = userRepository.findById(Long.valueOf(user.getId()));
        if(userOptional.isPresent()) {
            return updateUser(userOptional.get(), user);
        }
        return UserResponse.builder()
                .responseCode(404)
                .msg("User with given id is not present")
                .build();
    }

    private UserResponse updateUser(UserCredential user, UserCredential userNew) {
        user.setFullName(userNew.getFullName());
        user.setUsername(userNew.getUsername());
        user.setPhoneNumber(userNew.getPhoneNumber());
        user.setAddress(userNew.getAddress());
        userRepository.save(user);
        return mapUserToUserResponse(user);
    }

    private UserResponse mapUserToUserResponse(UserCredential user1) {
        return UserResponse.builder()
                .user(UserDto.builder()
                        .id(user1.getId())
                        .fullName(user1.getFullName())
                        .email(user1.getUsername())
                        .phoneNumber(user1.getPhoneNumber())
                        .address(user1.getAddress())
                        .userRole(user1.getUserRole())
                        .build())
                .responseCode(200)
                .msg("Success")
                .build();
    }

    public List<DeliveryAgent> getDeliveryAgents() {
        List<UserCredential> allUsers = userRepository.findAll();
        allUsers.stream().filter(userCredential -> userCredential.getUserRole().equals(UserRole.DELIVERY_AGENT));

        return allUsers.stream().map(user -> DeliveryAgent.builder()
                .name(user.getFullName())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .build()).toList();
    }

    public String getUserRole(String username) {
        Optional<UserCredential> userCredential = userRepository.findByUsername(username);
        return userCredential.map(credential -> credential.getUserRole().toString()).orElse(null);
    }


    public void validateToken(String token) {

    }
}
