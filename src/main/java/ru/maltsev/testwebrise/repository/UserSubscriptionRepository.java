package ru.maltsev.testwebrise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maltsev.testwebrise.entity.UserSubscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findByUserId(Long userId);
    Optional<UserSubscription> findByUserIdAndSubscriptionId(Long userId, Long subscriptionId);
    void deleteByUserIdAndSubscriptionId(Long userId, Long subscriptionId);
    boolean existsByUserIdAndSubscriptionId(Long userId, Long subscriptionId);
} 