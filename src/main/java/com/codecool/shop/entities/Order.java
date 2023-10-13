package com.codecool.shop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private String status;

    @Column(name = "address")
    private String address;
}