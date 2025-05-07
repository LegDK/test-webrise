package ru.maltsev.testwebrise.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maltsev.testwebrise.dto.SubscriptionDto;
import ru.maltsev.testwebrise.service.SubscriptionService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionDto>> getTopSubscriptions() {
        log.debug("Получение топ-3 популярных подписок");
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
} 