package com.example.webshop.dto;

import com.example.webshop.entities.User;
import org.modelmapper.ModelMapper;

public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convert(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
