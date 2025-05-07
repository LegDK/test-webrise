package ru.maltsev.testwebrise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.maltsev.testwebrise.dto.UserSubscriptionDto;
import ru.maltsev.testwebrise.entity.UserSubscription;

@Mapper(componentModel = "spring", uses = {SubscriptionMapper.class})
public interface UserSubscriptionMapper {
    
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "subscriptionId", source = "subscription.id")
    UserSubscriptionDto toDto(UserSubscription userSubscription);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "subscription", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserSubscription toEntity(UserSubscriptionDto userSubscriptionDto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "subscription", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(UserSubscriptionDto userSubscriptionDto, @MappingTarget UserSubscription userSubscription);
} 