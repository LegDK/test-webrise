package ru.maltsev.testwebrise.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maltsev.testwebrise.dto.UserSubscriptionDto;
import ru.maltsev.testwebrise.service.UserSubscriptionService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;

    @GetMapping
    public ResponseEntity<List<UserSubscriptionDto>> getUserSubscriptions(@PathVariable Long userId) {
        log.debug("Получение подписок пользователя с id: {}", userId);
        return ResponseEntity.ok(userSubscriptionService.getUserSubscriptions(userId));
    }

    @PostMapping
    public ResponseEntity<UserSubscriptionDto> addSubscription(
            @PathVariable Long userId,
            @RequestParam Long subscriptionId) {
        log.debug("Добавление подписки с id: {} пользователю с id: {}", subscriptionId, userId);
        return new ResponseEntity<>(
                userSubscriptionService.addSubscription(userId, subscriptionId),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<Void> removeSubscription(
            @PathVariable Long userId,
            @PathVariable Long subId) {
        log.debug("Удаление подписки с id: {} у пользователя с id: {}", subId, userId);
        userSubscriptionService.removeSubscription(userId, subId);
        return ResponseEntity.noContent().build();
    }
} 