package com.codecool.shop.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "supplier")
@Getter@Setter
@ToString
public class SupplierDataBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public SupplierDataBase(String name) {
        this.name = name;
    }

    public SupplierDataBase() {

    }
}