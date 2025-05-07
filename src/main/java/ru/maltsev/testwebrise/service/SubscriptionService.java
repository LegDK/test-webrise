package ru.maltsev.testwebrise.service;

import ru.maltsev.testwebrise.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionDto> getTopSubscriptions();
} 