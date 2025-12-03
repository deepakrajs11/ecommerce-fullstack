package com.ecommerce.userservice.mapper;


import com.ecommerce.userservice.dto.RegisterRequestDto;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.model.User;

public class UserMapper {
    public User requestUserMapper(RegisterRequestDto request, User user){
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setPassword(request.getPassword());
    return user;
    }

    public UserDto userUserDtoMapper(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
