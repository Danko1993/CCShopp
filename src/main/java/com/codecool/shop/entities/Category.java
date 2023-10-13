package com.codecool.shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }
}