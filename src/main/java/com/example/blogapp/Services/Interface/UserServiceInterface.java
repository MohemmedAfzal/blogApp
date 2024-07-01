package com.example.blogapp.Services.Interface;

import com.example.blogapp.payloads.UserDto;

import java.util.List;

public interface UserServiceInterface {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
