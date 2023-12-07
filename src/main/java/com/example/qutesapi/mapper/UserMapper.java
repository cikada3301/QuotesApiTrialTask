package com.example.qutesapi.mapper;

import com.example.qutesapi.dto.UserRegistrationDto;
import com.example.qutesapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userRegistrationDtoToUser(UserRegistrationDto userRegistrationDto);
}
