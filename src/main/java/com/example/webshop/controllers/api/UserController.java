package com.example.webshop.controllers.api;

import com.example.webshop.dto.UserDto;
import com.example.webshop.dto.UserMapper;
import com.example.webshop.entities.Item;
import com.example.webshop.entities.User;
import com.example.webshop.exceptions.NoSuchUserException;
import com.example.webshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.findAll().stream().map(user -> userMapper.convert(user)).toList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        Optional<User> userOptional = userService.getUser(id);
        if (userOptional.isEmpty()) {
            throw new NoSuchUserException(String.format("User with id %d was not found", id));
        }
        User user = userOptional.get();
        return userMapper.convert(user);
    }

    @GetMapping("/items/{id}")
    public List<Item> getUserItems(@PathVariable long id) {
        Optional<User> userOptional = userService.getUser(id);
        if (userOptional.isEmpty()) {
            throw new NoSuchUserException(String.format("User with id %d was not found", id));
        }
        User user = userOptional.get();
        return user.getItems();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/id")
    public String deleteUser(@PathVariable long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty()) {
            throw new NoSuchUserException(String.format("User with id %d was not found", id));
        }
        userService.delete(id);
        return "User with id " + id + "was deleted";
    }
}
