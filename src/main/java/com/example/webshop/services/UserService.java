package com.example.webshop.services;

import com.example.webshop.entities.User;
import com.example.webshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User saveUser(User user){
       return userRepository.save(user);
    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User update(User user){
       return userRepository.save(user);
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}


