package com.dhruv.ms.userService.dto;

import com.dhruv.ms.userService.model.Address;
import com.dhruv.ms.userService.model.UserRole;
import lombok.Builder;

@Builder
public record UserDto(String firstName, String lastName, String email, Long phoneNumber, Address address, UserRole userRole) {
}
