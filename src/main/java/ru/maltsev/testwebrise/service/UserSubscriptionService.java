package ru.maltsev.testwebrise.service;

import ru.maltsev.testwebrise.dto.UserSubscriptionDto;

import java.util.List;

public interface UserSubscriptionService {
    List<UserSubscriptionDto> getUserSubscriptions(Long userId);
    UserSubscriptionDto addSubscription(Long userId, Long subscriptionId);
    void removeSubscription(Long userId, Long subscriptionId);
} 