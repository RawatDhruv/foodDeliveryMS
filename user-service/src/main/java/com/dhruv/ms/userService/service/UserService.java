package com.dhruv.ms.userService.service;

import com.dhruv.ms.userService.dto.UserDto;
import com.dhruv.ms.userService.dto.UserPasswordRequest;
import com.dhruv.ms.userService.dto.UserResponse;
import com.dhruv.ms.userService.model.Address;
import com.dhruv.ms.userService.model.User;
import com.dhruv.ms.userService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public String createUser(UserDto userDto) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(userDto.address());
        User user = User.builder().firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .userRole(userDto.userRole())
                .address(addresses)
                .email(userDto.email())
                .phoneNumber(userDto.phoneNumber())
                .build();
        userRepository.save(user);
        return "User with id: " + user.getId() + " created";
    }

    public UserResponse getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User user1 = user.get();
            return mapUserToUserResponse(user1);
        }
        return UserResponse.builder()
                .responseCode(404)
                .msg("User with given id is not present")
                .build();
    }

    private UserResponse mapUserToUserResponse(User user1) {
        return UserResponse.builder()
                .id(user1.getId())
                .userDto(UserDto.builder()
                        .firstName(user1.getFirstName())
                        .lastName(user1.getLastName())
                        .email(user1.getEmail())
                        .phoneNumber(user1.getPhoneNumber())
                        .address((Address) user1.getAddress())
                        .userRole(user1.getUserRole())
                        .build())
                .responseCode(200)
                .msg("Success")
                .build();
    }

    public UserResponse updateUser(User user) {
        if(user.getId() == null) {
            return UserResponse.builder()
                    .responseCode(400)
                    .msg("Please provide user id")
                    .build();
        }
        Optional<User> userOptional = userRepository.findById(user.getId());
        if(userOptional.isPresent()) {
            return updateUser(userOptional.get(), user);
        }
        return UserResponse.builder()
                .responseCode(404)
                .msg("User with given id is not present")
                .build();
    }

    private UserResponse updateUser(User user, User userNew) {
        user.setFirstName(userNew.getFirstName());
        user.setLastName(userNew.getLastName());
        user.setEmail(userNew.getEmail());
        user.setPhoneNumber(userNew.getPhoneNumber());
        user.setAddress(userNew.getAddress());
        userRepository.save(user);
        return mapUserToUserResponse(user);
    }

    private String hashPassword(String password) {
        //to add some hashing mechanism.
        return password;
    }

    @Transactional
    public String setUserPassword(UserPasswordRequest userPasswordRequest) {
        int updatedRows = userRepository.updatePasswordById(userPasswordRequest.id(), hashPassword(userPasswordRequest.password()));

        if (updatedRows == 0) {
            return "User not found with ID: " + userPasswordRequest.id();
        }
        return "Password updated successfully";
    }
}
