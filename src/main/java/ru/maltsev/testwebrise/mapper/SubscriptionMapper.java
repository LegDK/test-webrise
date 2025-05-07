package ru.maltsev.testwebrise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.maltsev.testwebrise.dto.SubscriptionDto;
import ru.maltsev.testwebrise.entity.Subscription;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    
    SubscriptionDto toDto(Subscription subscription);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Subscription toEntity(SubscriptionDto subscriptionDto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(SubscriptionDto subscriptionDto, @MappingTarget Subscription subscription);
} 