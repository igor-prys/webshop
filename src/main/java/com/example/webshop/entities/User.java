package com.example.webshop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String nickname;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Item> items;
}
