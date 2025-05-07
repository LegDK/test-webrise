package ru.maltsev.testwebrise.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionDto {
    private Long id;
    
    @NotNull(message = "ID пользователя обязателен")
    private Long userId;
    
    @NotNull(message = "ID подписки обязателен")
    private Long subscriptionId;
    
    private SubscriptionDto subscription;
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean active;
    private LocalDateTime createdAt;
} 