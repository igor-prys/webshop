package com.example.webshop.controllers.mvc;

import com.example.webshop.dto.UserMapper;
import com.example.webshop.entities.User;
import com.example.webshop.exceptions.NoSuchUserException;
import com.example.webshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users2")
public class MvcUserController2 {

   private UserService userService;
   private UserMapper userMapper;

   @Autowired
    public MvcUserController2(UserService userService,UserMapper userMapper){
       this.userService=userService;
       this.userMapper=userMapper;
   }

   @GetMapping
    public String getAllUsers(Model model){
       model.addAttribute("allUsers", userService.findAll());
       return "allUsers2";
   }
   @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id,Model model){
       Optional<User> user=userService.getUser(id);
       if(user.isEmpty()){
           throw new NoSuchUserException(String.format("user with id %d is not exist",id));
       }
       model.addAttribute("user",userMapper.convert(user.get()));
       return "user2";
   }

   @GetMapping("/new")
   public String getFormForNewUser(Model model){
      model.addAttribute("newUser",new User());
      return "newUser2";
   }

   @PostMapping()
   public String createUser(@ModelAttribute ("newUser") User user){
      userService.saveUser(user);
      return "redirect:/users2";
   }

   @GetMapping("/edit/{id}")
   public String getFormForEditUser(@PathVariable("id") long id, Model model){
      model.addAttribute("editUser",userService.getUser(id));
      return "editUser2";
   }

   @PatchMapping("/{id}")
   public String editUser(@PathVariable ("id") long id, @ModelAttribute("editUser") User user){
      userService.update(user);
      return "redirect/users2";
   }

   @DeleteMapping("/{id}")
   public String deleteUser(@PathVariable("id") long id){
      userService.delete(id);
      return "redirect:/users2";
   }
}
