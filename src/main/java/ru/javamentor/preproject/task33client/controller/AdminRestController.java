package ru.javamentor.preproject.task33client.controller;

import ru.javamentor.preproject.task33client.dto.UserDto;
import ru.javamentor.preproject.task33client.mapper.UserMapper;
import ru.javamentor.preproject.task33client.model.User;
import ru.javamentor.preproject.task33client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public AdminRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<String> getCompanyList() {
        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addNewUser(@RequestBody UserDto newUserDto) {
        if ("".equals(newUserDto.getName()) ||
                "".equals(newUserDto.getPosition()) ||
                "".equals(Integer.toString(newUserDto.getAge())) ||
                "".equals(newUserDto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User newUser = userMapper.getUserFromDto(newUserDto);
        User userIllBeBack = userService.addUser(newUser);
        UserDto userIllBeBackDto = userMapper.getUserDtoFromUser(userIllBeBack);
        return new ResponseEntity<>(userIllBeBackDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto updateUserDto) {
        User updateUser = userMapper.getUserFromDto(updateUserDto);
        User userIllBeBack = userService.updateUser(updateUser);
        UserDto userIllBeBackDto = userMapper.getUserDtoFromUser(userIllBeBack);
        return new ResponseEntity<>(userIllBeBackDto, HttpStatus.OK);
    }

}

