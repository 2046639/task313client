package ru.javamentor.preproject.task33client.mapper;


import ru.javamentor.preproject.task33client.dto.UserDto;
import ru.javamentor.preproject.task33client.model.User;

public interface UserMapper {
    User getUserFromDto(UserDto userDto);

    UserDto getUserDtoFromUser(User user);
}
