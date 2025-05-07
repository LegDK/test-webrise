package ru.maltsev.testwebrise.service;

import ru.maltsev.testwebrise.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
} 