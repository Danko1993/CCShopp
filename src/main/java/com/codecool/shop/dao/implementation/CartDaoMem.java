package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;


import java.util.HashMap;


public class CartDaoMem implements CartDao {

    private static CartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private HashMap<Product, Integer> products;
    private long totalAmount;

    private int totalCartPrice;

    public CartDaoMem(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem(new HashMap<>());
        }
        return instance;
    }


    @Override
    public HashMap<Product, Integer> getAll() {
        return products;
    }

    @Override
    public void addProduct(Product product, int quantity) {
        try {
            products.put(product,products.get(product)+quantity);
        }
        catch (NullPointerException e){
            products.put(product,quantity);
        }

    }
    public void changeProductQuantity(Product product, int quantity){
        if (quantity > 0){
            this.deleteProduct(product);
            products.put(product,quantity);
        }
        else{
            this.deleteProduct(product);
        }

    }

    @Override
    public int getTotalCartPrice() {
        this.setTotalPrice();
        return totalCartPrice;
    }

    public void setTotalPrice(){
        this.totalCartPrice=0;
        products.forEach((key,value) ->{
            this.totalCartPrice += Integer.parseInt(key.getDefaultPrice().toString()) * value;
        });
    }
    @Override
    public void deleteProduct(Product product) {
        products.remove(product);

    }
    public long totalAmount(){
        products.forEach((product, integer) -> {System.out.println(totalAmount);
            totalAmount+=product.getDefaultPrice().longValue()*integer.longValue();
            });
       return totalAmount;
    }
}
