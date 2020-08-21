package ru.javamentor.preproject.task33client.service;

import ru.javamentor.preproject.task33client.model.User;

public interface UserService {
    User addUser(User newUser);

    User updateUser(User user);

    String deleteUser(Long id);

    String listUser();
}
