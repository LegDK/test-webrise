package ru.maltsev.testwebrise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.maltsev.testwebrise.entity.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    
    @Query(value = "SELECT s.* FROM subscriptions s " +
            "JOIN user_subscriptions us ON s.id = us.subscription_id " +
            "GROUP BY s.id " +
            "ORDER BY COUNT(us.id) DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Subscription> findTopSubscriptions();
} 