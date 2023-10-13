package com.codecool.shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "shopping_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
}

