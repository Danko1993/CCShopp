package com.codecool.shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_details")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer user;

    @Column(name = "phone")
    private String phone;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "paypal_username")
    private String paypalUsername;

    @Column(name = "paypal_password")
    private String paypalPassword;
}