package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.HashMap;

public interface CartDao {

    HashMap<Product, Integer> getAll();

    void addProduct(Product product, int quantity);

    int getTotalCartPrice();

    void  changeProductQuantity(Product product, int quantity);

    void deleteProduct(Product product);

    long totalAmount();

}
