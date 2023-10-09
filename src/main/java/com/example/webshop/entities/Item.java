package com.example.webshop.entities;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private boolean isPresent;

    private Type type;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    private enum Type{
        TOY, FOOD, COSMETICS, OTHER;
    }
}
