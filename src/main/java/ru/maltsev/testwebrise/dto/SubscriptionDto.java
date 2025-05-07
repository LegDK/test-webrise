package ru.maltsev.testwebrise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long id;
    
    @NotBlank(message = "Название обязательно")
    @Size(max = 100, message = "Название не должно превышать 100 символов")
    private String name;
    
    private String description;
    
    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть положительной")
    private BigDecimal price;
    
    private LocalDateTime createdAt;
} 