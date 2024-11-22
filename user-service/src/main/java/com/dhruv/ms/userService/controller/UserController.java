package com.dhruv.ms.userService.controller;

import com.dhruv.ms.userService.dto.UserDto;
import com.dhruv.ms.userService.dto.UserResponse;
import com.dhruv.ms.userService.model.User;
import com.dhruv.ms.userService.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}