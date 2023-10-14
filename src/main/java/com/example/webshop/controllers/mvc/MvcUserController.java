package com.example.webshop.controllers.mvc;

import com.example.webshop.dto.UserDto;
import com.example.webshop.dto.UserMapper;
import com.example.webshop.entities.User;
import com.example.webshop.exceptions.NoSuchUserException;
import com.example.webshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class MvcUserController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    private MvcUserController(UserService userService, UserMapper userMapper){
        this.userService=userService;
        this.userMapper=userMapper;
    }

    @GetMapping
    public String getAll(Model model){
        List<UserDto> userDtoList=userService.findAll()
                .stream().map(user->userMapper.convert(user)).toList();
        model.addAttribute("users",userDtoList);
        return "userView/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model){
        Optional<User> user=userService.getUser(id);
        if(user.isEmpty()){
            throw new NoSuchUserException(String.format("User with id %d does not exist", id));
        }
        UserDto userDto=userMapper.convert(user.get());
        model.addAttribute("user",userDto);
        return "userView/user";
    }

    @GetMapping("/new")
    public String getFormForNewUser(@ModelAttribute ("newUser") User user){
        return "userView/newUser";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("newUser") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable ("id") long id){
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String getFormForEdit(Model model, @PathVariable ("id") long id){
        Optional<User> user=userService.getUser(id);
        if(user.isEmpty()){
            throw new NoSuchUserException(String.format("user with id %d does not exist",id));
        }
        model.addAttribute("editUser",user.get());
        return "userView/editUser";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute ("editUser") User user,@PathVariable ("id")long id){
        user.setId(id);
        userService.update(user);
        return "redirect:/users";
    }
}
