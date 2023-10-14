package com.example.webshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.*;

@Data
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
    @JsonBackReference
    private User user;

    public Item() {
    }

    public boolean isPresent() {
        return this.isPresent;
    }

    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Item;
    }


    private enum Type{
        TOY, FOOD, COSMETICS, OTHER;
    }
}
