package ru.maltsev.testwebrise.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maltsev.testwebrise.dto.UserDto;
import ru.maltsev.testwebrise.entity.User;
import ru.maltsev.testwebrise.exception.DuplicateResourceException;
import ru.maltsev.testwebrise.exception.ResourceNotFoundException;
import ru.maltsev.testwebrise.mapper.UserMapper;
import ru.maltsev.testwebrise.repository.UserRepository;
import ru.maltsev.testwebrise.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(Long id) {
        log.debug("Получение пользователя по id: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь", "id", id));
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.debug("Создание нового пользователя: {}", userDto);

        validateUserDuplicate(userRepository.existsByUsername(userDto.getUsername()), userDto, userRepository.existsByEmail(userDto.getEmail()));

        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        log.info("Пользователь успешно создан с id: {}", savedUser.getId());
        return userMapper.toDto(savedUser);
    }

    private void validateUserDuplicate(boolean existingUsername, UserDto userDto, boolean existingEmail) {
        if (existingUsername) {
            throw new DuplicateResourceException("Пользователь с именем " + userDto.getUsername() + " уже существует");
        }

        if (existingEmail) {
            throw new DuplicateResourceException("Пользователь с email " + userDto.getEmail() + " уже существует");
        }
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        log.debug("Обновление пользователя с id: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь", "id", id));

        validateUserDuplicate(!user.getUsername().equals(userDto.getUsername()) &&
                userRepository.existsByUsername(userDto.getUsername()), userDto, !user.getEmail().equals(userDto.getEmail()) &&
                userRepository.existsByEmail(userDto.getEmail()));

        userMapper.updateEntityFromDto(userDto, user);
        User updatedUser = userRepository.save(user);
        log.info("Пользователь успешно обновлен с id: {}", updatedUser.getId());
        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.debug("Удаление пользователя с id: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Пользователь", "id", id);
        }
        
        userRepository.deleteById(id);
        log.info("Пользователь успешно удален с id: {}", id);
    }
} 