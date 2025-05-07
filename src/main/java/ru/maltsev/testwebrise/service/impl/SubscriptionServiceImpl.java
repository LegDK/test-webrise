package ru.maltsev.testwebrise.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maltsev.testwebrise.dto.SubscriptionDto;
import ru.maltsev.testwebrise.exception.ResourceNotFoundException;
import ru.maltsev.testwebrise.mapper.SubscriptionMapper;
import ru.maltsev.testwebrise.repository.SubscriptionRepository;
import ru.maltsev.testwebrise.service.SubscriptionService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<SubscriptionDto> getTopSubscriptions() {
        log.debug("Получение топ-3 популярных подписок");
        return subscriptionRepository.findTopSubscriptions().stream()
                .map(subscriptionMapper::toDto)
                .toList();
    }
} 