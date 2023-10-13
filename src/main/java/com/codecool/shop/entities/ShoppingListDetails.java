package com.codecool.shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shopping_list_details")
@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class ShoppingListDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}
