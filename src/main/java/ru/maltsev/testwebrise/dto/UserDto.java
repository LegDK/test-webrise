package ru.maltsev.testwebrise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    
    @NotBlank(message = "Имя пользователя обязательно")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    private String username;
    
    @NotBlank(message = "Email обязателен")
    @Email(message = "Email должен быть корректным")
    @Size(max = 100, message = "Email не должен превышать 100 символов")
    private String email;
    
    @NotBlank(message = "Полное имя обязательно")
    @Size(max = 100, message = "Полное имя не должно превышать 100 символов")
    private String fullName;
} 