package com.dhruv.ms.authService.dto;

import com.dhruv.ms.authService.model.Address;
import com.dhruv.ms.authService.model.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private String id;
    private String fullName;
    private String email;
    private Long phoneNumber;
    private Address address;
    private UserRole userRole;
}