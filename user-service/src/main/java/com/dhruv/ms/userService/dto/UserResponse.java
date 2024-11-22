package com.dhruv.ms.userService.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record UserResponse(Long id,UserDto userDto,Integer responseCode, String msg) implements Serializable {
}
