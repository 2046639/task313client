package ru.javamentor.preproject.task33client.mapper.UserMapperImpl;

import ru.javamentor.preproject.task33client.dto.UserDto;
import ru.javamentor.preproject.task33client.mapper.UserMapper;
import ru.javamentor.preproject.task33client.model.Role;
import ru.javamentor.preproject.task33client.model.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User getUserFromDto(UserDto userDto) {
        User user = new User(userDto);
        Set<Role> newSetRoles = createRoleSet(userDto.getRoles());
        user.setRoles(newSetRoles);
        return user;
    }

    @Override
    public UserDto getUserDtoFromUser(User user) {
        Long userId = user.getId();
        UserDto userDto = new UserDto(user);
        userDto.setId(userId);
        return userDto;
    }

    private Role adminRole = new Role(1L, "ADMIN");
    private Role userRole = new Role(2L, "USER");

    private Set<Role> createRoleSet(String[] allRoles) {
        Set<Role> roles = new HashSet<>();
        if (Arrays.asList(allRoles).contains("ADMIN")) {
            roles.add(adminRole);
        }
        if (Arrays.asList(allRoles).contains("USER")) {
            roles.add(userRole);
        }
        return roles;
    }
}
