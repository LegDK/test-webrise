package ru.maltsev.testwebrise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.maltsev.testwebrise.dto.UserDto;
import ru.maltsev.testwebrise.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserDto toDto(User user);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserDto userDto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(UserDto userDto, @MappingTarget User user);
} 