package com.example.webshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;


    @Column(name = "is_Present")
    private boolean present;

    private Type type;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference
    private User user;

    public Item() {
    }

    private enum Type {
        TOY, FOOD, COSMETICS, OTHER;
    }
}
