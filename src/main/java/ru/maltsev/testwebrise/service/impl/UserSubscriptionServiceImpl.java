package ru.maltsev.testwebrise.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maltsev.testwebrise.dto.UserSubscriptionDto;
import ru.maltsev.testwebrise.entity.Subscription;
import ru.maltsev.testwebrise.entity.User;
import ru.maltsev.testwebrise.entity.UserSubscription;
import ru.maltsev.testwebrise.exception.DuplicateResourceException;
import ru.maltsev.testwebrise.exception.ResourceNotFoundException;
import ru.maltsev.testwebrise.mapper.UserSubscriptionMapper;
import ru.maltsev.testwebrise.repository.SubscriptionRepository;
import ru.maltsev.testwebrise.repository.UserRepository;
import ru.maltsev.testwebrise.repository.UserSubscriptionRepository;
import ru.maltsev.testwebrise.service.UserSubscriptionService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final UserSubscriptionMapper userSubscriptionMapper;

    @Override
    public List<UserSubscriptionDto> getUserSubscriptions(Long userId) {
        log.debug("Получение подписок пользователя с id: {}", userId);
        
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Пользователь", "id", userId);
        }
        
        return userSubscriptionRepository.findByUserId(userId).stream()
                .map(userSubscriptionMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UserSubscriptionDto addSubscription(Long userId, Long subscriptionId) {
        log.debug("Добавление подписки с id: {} пользователю с id: {}", subscriptionId, userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь", "id", userId));
        
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Подписка", "id", subscriptionId));
        
        if (userSubscriptionRepository.existsByUserIdAndSubscriptionId(userId, subscriptionId)) {
            throw new DuplicateResourceException("У пользователя уже есть эта подписка");
        }
        
        UserSubscription userSubscription = UserSubscription.builder()
                .user(user)
                .subscription(subscription)
                .active(true)
                .build();
        
        UserSubscription savedUserSubscription = userSubscriptionRepository.save(userSubscription);
        log.info("Подписка успешно добавлена пользователю с id: {}", userId);
        return userSubscriptionMapper.toDto(savedUserSubscription);
    }

    @Override
    @Transactional
    public void removeSubscription(Long userId, Long subscriptionId) {
        log.debug("Удаление подписки с id: {} у пользователя с id: {}", subscriptionId, userId);
        
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Пользователь", "id", userId);
        }
        
        if (!subscriptionRepository.existsById(subscriptionId)) {
            throw new ResourceNotFoundException("Подписка", "id", subscriptionId);
        }
        
        if (!userSubscriptionRepository.existsByUserIdAndSubscriptionId(userId, subscriptionId)) {
            throw new ResourceNotFoundException("Подписка не найдена у пользователя");
        }
        
        userSubscriptionRepository.deleteByUserIdAndSubscriptionId(userId, subscriptionId);
        log.info("Подписка успешно удалена у пользователя с id: {}", userId);
    }
} 