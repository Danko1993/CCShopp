package com.codecool.shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}
