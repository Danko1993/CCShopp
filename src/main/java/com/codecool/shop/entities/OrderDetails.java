package com.codecool.shop.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "order_details")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}

