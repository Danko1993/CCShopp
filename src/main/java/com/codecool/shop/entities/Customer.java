package com.codecool.shop.entities;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customer")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    public Customer(String email, String hashedpassword, byte[] salt) {
        this.email = email;
        this.hashedpassword = hashedpassword;
        this.salt = salt;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "email")
    private String email;

    @Column(name = "hashed_password")
    private String hashedpassword;

    @Column(name = "salt")
    private byte[] salt;
}

