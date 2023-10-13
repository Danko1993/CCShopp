package com.codecool.shop.service;


import com.codecool.shop.dao.CartDao;

import com.codecool.shop.model.Product;

import java.util.HashMap;


public class CartService {
    public CartDao cartDao;

    public CartService (CartDao cartDao){
        this.cartDao=cartDao;
    }
    public HashMap<Product, Integer> getProducts(){
        return cartDao.getAll();
    }
    public void addProduct(Product product, int quantity) {

        cartDao.addProduct(product, quantity);
    }
    public int getTotalPrice(){
        return cartDao.getTotalCartPrice();
    }
    public long totalAmount(){
        return cartDao.totalAmount();
    }

    public void changeProductQuantity(Product product, int quantity){
        this.cartDao.changeProductQuantity(product,quantity);
    }



}
